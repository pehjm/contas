/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.javaparser.ParseResult
 *  com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
 *  com.github.javaparser.ast.body.TypeDeclaration
 *  com.github.javaparser.utils.SourceRoot
 */
package org.apache.xmlbeans.impl.config;

import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.utils.SourceRoot;
import java.io.File;
import java.io.IOException;
import java.util.function.Predicate;

class Parser {
    final File[] javaFiles;
    final File[] classpath;

    public Parser(File[] javaFiles, File[] classpath) {
        this.javaFiles = javaFiles != null ? (File[])javaFiles.clone() : new File[]{};
        this.classpath = classpath != null ? (File[])classpath.clone() : new File[]{};
    }

    public ClassOrInterfaceDeclaration loadSource(String className) {
        String fileName = className.replace('.', '/') + ".java";
        for (File f : this.javaFiles) {
            String filePath = f.getPath();
            if (!filePath.replace('\\', '/').endsWith(fileName)) continue;
            String rootPath = filePath.substring(0, filePath.length() - fileName.length());
            String startPackage = className.indexOf(46) == -1 ? "" : className.substring(0, className.lastIndexOf(46));
            String simpleName = startPackage.isEmpty() ? className : className.substring(startPackage.length() + 1);
            SourceRoot sourceRoot = new SourceRoot(new File(rootPath).toPath());
            try {
                ParseResult pcu = sourceRoot.tryToParse(startPackage, simpleName + ".java");
                ClassOrInterfaceDeclaration cls = pcu.getResult().flatMap(cu -> cu.getTypes().stream().filter(Parser.matchType(className)).map(t -> (ClassOrInterfaceDeclaration)t).findFirst()).orElse(null);
                return cls;
            }
            catch (IOException e) {
                return null;
            }
        }
        return null;
    }

    private static Predicate<TypeDeclaration<?>> matchType(String className) {
        return t -> t instanceof ClassOrInterfaceDeclaration && t.getFullyQualifiedName().map(fqn -> fqn.equals(className)).orElse(false) != false;
    }
}

