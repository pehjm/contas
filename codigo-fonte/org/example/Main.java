/*
 * Decompiled with CFR 0.152.
 */
package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> cpfs = new ArrayList<String>();
        ArrayList<String> numeroRaizCpfPessoa = new ArrayList<String>();
        ArrayList<String> digitoCpfPessoa = new ArrayList<String>();
        ArrayList<String> cpfsCadastrados = new ArrayList<String>();
        ArrayList<String> cpfsCriadosComSucesso = new ArrayList<String>();
        ArrayList<Integer> codigoPessoaBCP = new ArrayList<Integer>();
        Scanner scanner = new Scanner(System.in);
        System.err.println("Digite seu email para receber o c\u00f3digo OPT e habilitar o login: ");
        String email = scanner.nextLine();
        System.err.println("Digite o ddd do numero de telefone para receber o c\u00f3digo OPT e habilitar o login: ");
        String dddTelefone = scanner.nextLine();
        System.err.println("Digite seu telefone para receber o c\u00f3digo OPT e habilitar o login: ");
        String telefone = scanner.nextLine();
        System.err.println("Digite a quantidade de CPF's que vc deseja criar: ");
        String token = Main.geraToken();
        System.err.println("Gerando cpfs e realizando cadastro...");
        for (int quantidadeDeMassas = Integer.parseInt(scanner.nextLine()); quantidadeDeMassas > 0; --quantidadeDeMassas) {
            cpfs.add(Main.geraCpf());
        }
        for (String cpf : cpfs) {
            numeroRaizCpfPessoa.add(cpf.substring(0, 9));
            digitoCpfPessoa.add(cpf.substring(9));
        }
        for (int i = 0; i < cpfs.size(); ++i) {
            String nome = Main.gerarNomeAleatorio();
            Response response = Main.integrarBCC(nome, (String)numeroRaizCpfPessoa.get(i), (String)digitoCpfPessoa.get(i), email, dddTelefone, telefone, token);
            if (response.statusCode() == 400) {
                cpfsCadastrados.add((String)numeroRaizCpfPessoa.get(i) + (String)digitoCpfPessoa.get(i));
                continue;
            }
            if (response.statusCode() != 200) continue;
            cpfsCriadosComSucesso.add((String)numeroRaizCpfPessoa.get(i) + (String)digitoCpfPessoa.get(i));
            codigoPessoaBCP.add((Integer)response.jsonPath().get("pessoa.codigoPessoaBCP"));
        }
        Main.criaPlanilhaCpfsJaCadastrados(cpfsCadastrados);
        Main.criaPlanilhaContasCadastradas(cpfsCriadosComSucesso, codigoPessoaBCP);
        scanner.close();
    }

    private static String geraToken() {
        RestAssured.baseURI = "https://apihlg-portoseg.portoseguro.com.br/oauth-bcc/v1";
        String corpoRequisicao = "{\n    \"grant_type\" : \"client_credentials\"\n}";
        Response response = (Response)RestAssured.given().header("Content-Type", "application/json", new Object[0]).header("Authorization", "Basic MWZkMTExYWUtMTY3Zi00YTBhLTkyZWEtZjU2Njg3MDNhMTVlOjJlZWExYzk5LTFkNWYtNGY0OC1hMDhmLWE4ZTYzYmQzOWM2Nw==", new Object[0]).body(corpoRequisicao).post("/access-token", new Object[0]);
        return response.jsonPath().getString("access_token");
    }

    private static Response integrarBCC(String nome, String numeroRaizCpfPessoa, String digitoCpfPessoa, String email, String dddTelefone, String telefone, String token) {
        RestAssured.baseURI = "https://apihlg-portoseg.portoseguro.com.br/bcc/v1";
        String corpoRequisicao = "{\n    \"codigoOrigemBCP\": 165,\n    \"nomeLegalPessoa\": \"" + nome + "\",\n    \"tipoPessoa\": {\n        \"codigoTipoPessoa\": 1\n    },\n    \"sexo\": {\n        \"codigoSexo\": 1\n    },\n    \"dataNascimentoPessoaFisica\": \"1994-05-06\",\n    \"documentosIdentificacao\": [\n        {\n            \"tipoDocumentoIdentificacao\": {\n                \"codigoTipoDocumentoIdentificacao\": 1\n            },\n            \"numeroRaizCpfPessoa\": \"" + numeroRaizCpfPessoa + "\",\n            \"digitoCpfPessoa\": \"" + digitoCpfPessoa + "\",\n            \"orgaoExpedidorDocumento\": {\n                \"codigoOrgaoExpedidorDocumento\": 10\n            }\n        }\n    ],\n    \"enderecosEletronicos\": [\n        {\n            \"tipoFinalidadeEnderecoEletronico\": {\n                \"codigoTipoFinalidadeEnderecoEletronico\": 1,\n                \"nomeTipoFinalidadeEnderecoEletronico\": \"Profissional\"\n            },\n            \"textoEnderecoEletronico\": \"" + email + "\"\n        }\n    ],\n    \"telefones\": [\n        {\n            \"codigoPessoa\": 1,\n            \"codigoDDDTelefone\": \"" + dddTelefone + "\",\n            \"codigoDDITelefone\": 55, \n            \"numeroTelefone\": \"" + telefone + "\",\n            \"tipoLinhaTelefonica\": {\n                \"codigoTipoLinhaTelefonica\": 1\n            },\n            \"tipoFinalidadeTelefone\": {\n                \"codigoTipoFinalidadeTelefone\": 1\n            }\n        }\n    ]\n}";
        Response response = (Response)RestAssured.given().header("Content-Type", "application/json", new Object[0]).header("client_id", "1fd111ae-167f-4a0a-92ea-f5668703a15e", new Object[0]).header("access_token", token, new Object[0]).body(corpoRequisicao).post("/person-data", new Object[0]);
        return response;
    }

    public static String gerarNomeAleatorio() {
        String[] PREFIXOS = new String[]{"Ana", "Carlos", "Mariana", "Pedro", "Isabel", "Rafael", "Juliana", "Lucas", "Beatriz", "Fernando", "Camila", "Gustavo", "Amanda", "Rodrigo", "Vanessa", "Renato", "Cristina", "Tiago", "Laura", "Vin\u00edcius", "Carolina", "Leandro", "Nat\u00e1lia", "Andr\u00e9", "Larissa", "Jos\u00e9", "Patr\u00edcia", "Daniel", "Gabriela", "Ricardo", "Thais", "Marcelo", "Renata", "Felipe", "Vivian", "Eduardo", "Ana Clara", "Luiz", "Bianca", "Hugo", "Let\u00edcia"};
        String[] SUFIXOS = new String[]{"Silva", "Santos", "Oliveira", "Lima", "Pereira", "Costa", "Souza", "Ferreira", "Almeida", "Rocha", "Melo", "Ara\u00fajo", "Mendes", "Pinto", "Monteiro", "Freitas", "Correia", "Nunes", "Dias", "Cavalcante", "Gomes", "Pires", "Barbosa", "Ribeiro", "Lopes", "Figueiredo", "Castro", "Mota", "Farias", "Cunha", "Moura", "Cardoso", "Machado", "Martins", "Nascimento", "Sales", "Teixeira", "Carvalho", "Nogueira", "Vieira"};
        Random random = new Random();
        String prefixo = PREFIXOS[random.nextInt(PREFIXOS.length)];
        String sufixo = SUFIXOS[random.nextInt(SUFIXOS.length)];
        return prefixo + " " + sufixo;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void criaPlanilhaCpfsJaCadastrados(List<String> cpfsCadastrados) {
        if (cpfsCadastrados.isEmpty()) {
            return;
        }
        System.out.println("Cpf's j\u00e1 cadastrados " + cpfsCadastrados);
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("PlanilhaCPFs");
        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Cpf's j\u00e1 cadastrados");
        int rowNum = 1;
        for (String cpf : cpfsCadastrados) {
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(cpf);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String dataHoraAtual = dateFormat.format(new Date());
        String nomeArquivo = "PlanilhaCPFs_" + dataHoraAtual + ".xlsx";
        try (FileOutputStream fileOut = new FileOutputStream(nomeArquivo);){
            workbook.write(fileOut);
            System.out.println("Planilha criada com sucesso: " + nomeArquivo);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                workbook.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void criaPlanilhaContasCadastradas(List<String> cpfsCadastrados, List<Integer> contasCadastradas) {
        if (cpfsCadastrados.isEmpty()) {
            return;
        }
        System.out.println("Contas cadastradas: " + cpfsCadastrados);
        System.out.println("Codigos Bcps Cadastrados: " + contasCadastradas);
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Contas Cadastradas");
        Row headerRow = sheet.createRow(0);
        Cell headerCellCpfs = headerRow.createCell(0);
        headerCellCpfs.setCellValue("Cpf's");
        Cell headerCellCodigosBCP = headerRow.createCell(1);
        headerCellCodigosBCP.setCellValue("CodigoBCP");
        int rowNum = 1;
        for (int i = 0; i < cpfsCadastrados.size(); ++i) {
            Row row = sheet.createRow(rowNum++);
            Cell cpfCell = row.createCell(0);
            cpfCell.setCellValue(cpfsCadastrados.get(i));
            Cell codigoBCPCell = row.createCell(1);
            codigoBCPCell.setCellValue(contasCadastradas.get(i).intValue());
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String dataHoraAtual = dateFormat.format(new Date());
        String nomeArquivo = "Contas" + dataHoraAtual + ".xlsx";
        try (FileOutputStream fileOut = new FileOutputStream(nomeArquivo);){
            workbook.write(fileOut);
            System.out.println("Planilha criada com sucesso: " + nomeArquivo);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                workbook.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static int randomiza(int n) {
        int ranNum = (int)(Math.random() * (double)n);
        return ranNum;
    }

    private static int mod(int dividendo, int divisor) {
        return (int)Math.round((double)dividendo - Math.floor(dividendo / divisor) * (double)divisor);
    }

    public static String geraCpf() {
        int n = 9;
        int n1 = Main.randomiza(n);
        int n2 = Main.randomiza(n);
        int n3 = Main.randomiza(n);
        int n4 = Main.randomiza(n);
        int n5 = Main.randomiza(n);
        int n6 = Main.randomiza(n);
        int n7 = Main.randomiza(n);
        int n8 = Main.randomiza(n);
        int n9 = Main.randomiza(n);
        int d1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;
        if ((d1 = 11 - Main.mod(d1, 11)) >= 10) {
            d1 = 0;
        }
        int d2 = d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;
        d2 = 11 - Main.mod(d2, 11);
        String retorno = null;
        if (d2 >= 10) {
            d2 = 0;
        }
        retorno = "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + d1 + d2;
        return retorno;
    }
}

