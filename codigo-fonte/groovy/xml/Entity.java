/*
 * Decompiled with CFR 0.152.
 */
package groovy.xml;

import groovy.lang.Buildable;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.transform.Internal;
import java.beans.Transient;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;

public class Entity
implements Buildable,
GroovyObject {
    public static final Entity nbsp;
    public static final Entity iexcl;
    public static final Entity cent;
    public static final Entity pound;
    public static final Entity curren;
    public static final Entity yen;
    public static final Entity brvbar;
    public static final Entity sect;
    public static final Entity uml;
    public static final Entity copy;
    public static final Entity ordf;
    public static final Entity laquo;
    public static final Entity not;
    public static final Entity shy;
    public static final Entity reg;
    public static final Entity macr;
    public static final Entity deg;
    public static final Entity plusmn;
    public static final Entity sup2;
    public static final Entity sup3;
    public static final Entity acute;
    public static final Entity micro;
    public static final Entity para;
    public static final Entity middot;
    public static final Entity cedil;
    public static final Entity sup1;
    public static final Entity ordm;
    public static final Entity raquo;
    public static final Entity frac14;
    public static final Entity frac12;
    public static final Entity frac34;
    public static final Entity iquest;
    public static final Entity Agrave;
    public static final Entity Aacute;
    public static final Entity Acirc;
    public static final Entity Atilde;
    public static final Entity Auml;
    public static final Entity Aring;
    public static final Entity AElig;
    public static final Entity Ccedil;
    public static final Entity Egrave;
    public static final Entity Eacute;
    public static final Entity Ecirc;
    public static final Entity Euml;
    public static final Entity Igrave;
    public static final Entity Iacute;
    public static final Entity Icirc;
    public static final Entity Iuml;
    public static final Entity ETH;
    public static final Entity Ntilde;
    public static final Entity Ograve;
    public static final Entity Oacute;
    public static final Entity Ocirc;
    public static final Entity Otilde;
    public static final Entity Ouml;
    public static final Entity times;
    public static final Entity Oslash;
    public static final Entity Ugrave;
    public static final Entity Uacute;
    public static final Entity Ucirc;
    public static final Entity Uuml;
    public static final Entity Yacute;
    public static final Entity THORN;
    public static final Entity szlig;
    public static final Entity agrave;
    public static final Entity aacute;
    public static final Entity acirc;
    public static final Entity atilde;
    public static final Entity auml;
    public static final Entity aring;
    public static final Entity aelig;
    public static final Entity ccedil;
    public static final Entity egrave;
    public static final Entity eacute;
    public static final Entity ecirc;
    public static final Entity euml;
    public static final Entity igrave;
    public static final Entity iacute;
    public static final Entity icirc;
    public static final Entity iuml;
    public static final Entity eth;
    public static final Entity ntilde;
    public static final Entity ograve;
    public static final Entity oacute;
    public static final Entity ocirc;
    public static final Entity otilde;
    public static final Entity ouml;
    public static final Entity divide;
    public static final Entity oslash;
    public static final Entity ugrave;
    public static final Entity uacute;
    public static final Entity ucirc;
    public static final Entity uuml;
    public static final Entity yacute;
    public static final Entity thorn;
    public static final Entity yuml;
    public static final Entity lt;
    public static final Entity gt;
    public static final Entity amp;
    public static final Entity apos;
    public static final Entity quot;
    public static final Entity OElig;
    public static final Entity oelig;
    public static final Entity Scaron;
    public static final Entity scaron;
    public static final Entity Yuml;
    public static final Entity circ;
    public static final Entity tilde;
    public static final Entity ensp;
    public static final Entity emsp;
    public static final Entity thinsp;
    public static final Entity zwnj;
    public static final Entity zwj;
    public static final Entity lrm;
    public static final Entity rlm;
    public static final Entity ndash;
    public static final Entity mdash;
    public static final Entity lsquo;
    public static final Entity rsquo;
    public static final Entity sbquo;
    public static final Entity ldquo;
    public static final Entity rdquo;
    public static final Entity bdquo;
    public static final Entity dagger;
    public static final Entity Dagger;
    public static final Entity permil;
    public static final Entity lsaquo;
    public static final Entity rsaquo;
    public static final Entity euro;
    private final Object entity;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;

    public Entity(String name) {
        MetaClass metaClass;
        this.metaClass = metaClass = this.$getStaticMetaClass();
        GStringImpl gStringImpl = new GStringImpl(new Object[]{name}, new String[]{"&", ";"});
        this.entity = gStringImpl;
    }

    public Entity(int name) {
        MetaClass metaClass;
        this.metaClass = metaClass = this.$getStaticMetaClass();
        GStringImpl gStringImpl = new GStringImpl(new Object[]{name}, new String[]{"&#", ";"});
        this.entity = gStringImpl;
    }

    @Override
    public void build(GroovyObject builder) {
        IndyInterface.bootstrap("invoke", "leftShift", 0, IndyInterface.bootstrap("getProperty", "unescaped", 0, builder), this.entity);
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != Entity.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo classInfo = $staticClassInfo;
        if (classInfo == null) {
            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
        }
        return classInfo.getMetaClass();
    }

    @Override
    @Generated
    @Internal
    @Transient
    public MetaClass getMetaClass() {
        MetaClass metaClass = this.metaClass;
        if (metaClass != null) {
            return metaClass;
        }
        this.metaClass = this.$getStaticMetaClass();
        return this.metaClass;
    }

    @Override
    @Generated
    @Internal
    public void setMetaClass(MetaClass metaClass) {
        this.metaClass = metaClass;
    }

    public static /* synthetic */ MethodHandles.Lookup $getLookup() {
        return MethodHandles.lookup();
    }

    static {
        CallSite callSite = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "nbsp");
        nbsp = IndyInterface.bootstrap("cast", "()", 0, callSite);
        CallSite callSite2 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "iexcl");
        iexcl = IndyInterface.bootstrap("cast", "()", 0, callSite2);
        CallSite callSite3 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "cent");
        cent = IndyInterface.bootstrap("cast", "()", 0, callSite3);
        CallSite callSite4 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "pound");
        pound = IndyInterface.bootstrap("cast", "()", 0, callSite4);
        CallSite callSite5 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "curren");
        curren = IndyInterface.bootstrap("cast", "()", 0, callSite5);
        CallSite callSite6 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "yen");
        yen = IndyInterface.bootstrap("cast", "()", 0, callSite6);
        CallSite callSite7 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "brvbar");
        brvbar = IndyInterface.bootstrap("cast", "()", 0, callSite7);
        CallSite callSite8 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "sect");
        sect = IndyInterface.bootstrap("cast", "()", 0, callSite8);
        CallSite callSite9 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "uml");
        uml = IndyInterface.bootstrap("cast", "()", 0, callSite9);
        CallSite callSite10 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "copy");
        copy = IndyInterface.bootstrap("cast", "()", 0, callSite10);
        CallSite callSite11 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "ordf");
        ordf = IndyInterface.bootstrap("cast", "()", 0, callSite11);
        CallSite callSite12 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "laquo");
        laquo = IndyInterface.bootstrap("cast", "()", 0, callSite12);
        CallSite callSite13 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "not");
        not = IndyInterface.bootstrap("cast", "()", 0, callSite13);
        CallSite callSite14 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "shy");
        shy = IndyInterface.bootstrap("cast", "()", 0, callSite14);
        CallSite callSite15 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "reg");
        reg = IndyInterface.bootstrap("cast", "()", 0, callSite15);
        CallSite callSite16 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "macr");
        macr = IndyInterface.bootstrap("cast", "()", 0, callSite16);
        CallSite callSite17 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "deg");
        deg = IndyInterface.bootstrap("cast", "()", 0, callSite17);
        CallSite callSite18 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "plusmn");
        plusmn = IndyInterface.bootstrap("cast", "()", 0, callSite18);
        CallSite callSite19 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "sup2");
        sup2 = IndyInterface.bootstrap("cast", "()", 0, callSite19);
        CallSite callSite20 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "sup3");
        sup3 = IndyInterface.bootstrap("cast", "()", 0, callSite20);
        CallSite callSite21 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "acute");
        acute = IndyInterface.bootstrap("cast", "()", 0, callSite21);
        CallSite callSite22 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "micro");
        micro = IndyInterface.bootstrap("cast", "()", 0, callSite22);
        CallSite callSite23 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "para");
        para = IndyInterface.bootstrap("cast", "()", 0, callSite23);
        CallSite callSite24 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "middot");
        middot = IndyInterface.bootstrap("cast", "()", 0, callSite24);
        CallSite callSite25 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "cedil");
        cedil = IndyInterface.bootstrap("cast", "()", 0, callSite25);
        CallSite callSite26 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "sup1");
        sup1 = IndyInterface.bootstrap("cast", "()", 0, callSite26);
        CallSite callSite27 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "ordm");
        ordm = IndyInterface.bootstrap("cast", "()", 0, callSite27);
        CallSite callSite28 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "raquo");
        raquo = IndyInterface.bootstrap("cast", "()", 0, callSite28);
        CallSite callSite29 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "frac14");
        frac14 = IndyInterface.bootstrap("cast", "()", 0, callSite29);
        CallSite callSite30 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "frac12");
        frac12 = IndyInterface.bootstrap("cast", "()", 0, callSite30);
        CallSite callSite31 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "frac34");
        frac34 = IndyInterface.bootstrap("cast", "()", 0, callSite31);
        CallSite callSite32 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "iquest");
        iquest = IndyInterface.bootstrap("cast", "()", 0, callSite32);
        CallSite callSite33 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Agrave");
        Agrave = IndyInterface.bootstrap("cast", "()", 0, callSite33);
        CallSite callSite34 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Aacute");
        Aacute = IndyInterface.bootstrap("cast", "()", 0, callSite34);
        CallSite callSite35 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Acirc");
        Acirc = IndyInterface.bootstrap("cast", "()", 0, callSite35);
        CallSite callSite36 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Atilde");
        Atilde = IndyInterface.bootstrap("cast", "()", 0, callSite36);
        CallSite callSite37 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Auml");
        Auml = IndyInterface.bootstrap("cast", "()", 0, callSite37);
        CallSite callSite38 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Aring");
        Aring = IndyInterface.bootstrap("cast", "()", 0, callSite38);
        CallSite callSite39 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "AElig");
        AElig = IndyInterface.bootstrap("cast", "()", 0, callSite39);
        CallSite callSite40 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Ccedil");
        Ccedil = IndyInterface.bootstrap("cast", "()", 0, callSite40);
        CallSite callSite41 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Egrave");
        Egrave = IndyInterface.bootstrap("cast", "()", 0, callSite41);
        CallSite callSite42 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Eacute");
        Eacute = IndyInterface.bootstrap("cast", "()", 0, callSite42);
        CallSite callSite43 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Ecirc");
        Ecirc = IndyInterface.bootstrap("cast", "()", 0, callSite43);
        CallSite callSite44 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Euml");
        Euml = IndyInterface.bootstrap("cast", "()", 0, callSite44);
        CallSite callSite45 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Igrave");
        Igrave = IndyInterface.bootstrap("cast", "()", 0, callSite45);
        CallSite callSite46 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Iacute");
        Iacute = IndyInterface.bootstrap("cast", "()", 0, callSite46);
        CallSite callSite47 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Icirc");
        Icirc = IndyInterface.bootstrap("cast", "()", 0, callSite47);
        CallSite callSite48 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Iuml");
        Iuml = IndyInterface.bootstrap("cast", "()", 0, callSite48);
        CallSite callSite49 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "ETH");
        ETH = IndyInterface.bootstrap("cast", "()", 0, callSite49);
        CallSite callSite50 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Ntilde");
        Ntilde = IndyInterface.bootstrap("cast", "()", 0, callSite50);
        CallSite callSite51 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Ograve");
        Ograve = IndyInterface.bootstrap("cast", "()", 0, callSite51);
        CallSite callSite52 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Oacute");
        Oacute = IndyInterface.bootstrap("cast", "()", 0, callSite52);
        CallSite callSite53 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Ocirc");
        Ocirc = IndyInterface.bootstrap("cast", "()", 0, callSite53);
        CallSite callSite54 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Otilde");
        Otilde = IndyInterface.bootstrap("cast", "()", 0, callSite54);
        CallSite callSite55 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Ouml");
        Ouml = IndyInterface.bootstrap("cast", "()", 0, callSite55);
        CallSite callSite56 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "times");
        times = IndyInterface.bootstrap("cast", "()", 0, callSite56);
        CallSite callSite57 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Oslash");
        Oslash = IndyInterface.bootstrap("cast", "()", 0, callSite57);
        CallSite callSite58 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Ugrave");
        Ugrave = IndyInterface.bootstrap("cast", "()", 0, callSite58);
        CallSite callSite59 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Uacute");
        Uacute = IndyInterface.bootstrap("cast", "()", 0, callSite59);
        CallSite callSite60 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Ucirc");
        Ucirc = IndyInterface.bootstrap("cast", "()", 0, callSite60);
        CallSite callSite61 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Uuml");
        Uuml = IndyInterface.bootstrap("cast", "()", 0, callSite61);
        CallSite callSite62 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Yacute");
        Yacute = IndyInterface.bootstrap("cast", "()", 0, callSite62);
        CallSite callSite63 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "THORN");
        THORN = IndyInterface.bootstrap("cast", "()", 0, callSite63);
        CallSite callSite64 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "szlig");
        szlig = IndyInterface.bootstrap("cast", "()", 0, callSite64);
        CallSite callSite65 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "agrave");
        agrave = IndyInterface.bootstrap("cast", "()", 0, callSite65);
        CallSite callSite66 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "aacute");
        aacute = IndyInterface.bootstrap("cast", "()", 0, callSite66);
        CallSite callSite67 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "acirc");
        acirc = IndyInterface.bootstrap("cast", "()", 0, callSite67);
        CallSite callSite68 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "atilde");
        atilde = IndyInterface.bootstrap("cast", "()", 0, callSite68);
        CallSite callSite69 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "auml");
        auml = IndyInterface.bootstrap("cast", "()", 0, callSite69);
        CallSite callSite70 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "aring");
        aring = IndyInterface.bootstrap("cast", "()", 0, callSite70);
        CallSite callSite71 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "aelig");
        aelig = IndyInterface.bootstrap("cast", "()", 0, callSite71);
        CallSite callSite72 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "ccedil");
        ccedil = IndyInterface.bootstrap("cast", "()", 0, callSite72);
        CallSite callSite73 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "egrave");
        egrave = IndyInterface.bootstrap("cast", "()", 0, callSite73);
        CallSite callSite74 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "eacute");
        eacute = IndyInterface.bootstrap("cast", "()", 0, callSite74);
        CallSite callSite75 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "ecirc");
        ecirc = IndyInterface.bootstrap("cast", "()", 0, callSite75);
        CallSite callSite76 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "euml");
        euml = IndyInterface.bootstrap("cast", "()", 0, callSite76);
        CallSite callSite77 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "igrave");
        igrave = IndyInterface.bootstrap("cast", "()", 0, callSite77);
        CallSite callSite78 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "iacute");
        iacute = IndyInterface.bootstrap("cast", "()", 0, callSite78);
        CallSite callSite79 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "icirc");
        icirc = IndyInterface.bootstrap("cast", "()", 0, callSite79);
        CallSite callSite80 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "iuml");
        iuml = IndyInterface.bootstrap("cast", "()", 0, callSite80);
        CallSite callSite81 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "eth");
        eth = IndyInterface.bootstrap("cast", "()", 0, callSite81);
        CallSite callSite82 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "ntilde");
        ntilde = IndyInterface.bootstrap("cast", "()", 0, callSite82);
        CallSite callSite83 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "ograve");
        ograve = IndyInterface.bootstrap("cast", "()", 0, callSite83);
        CallSite callSite84 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "oacute");
        oacute = IndyInterface.bootstrap("cast", "()", 0, callSite84);
        CallSite callSite85 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "ocirc");
        ocirc = IndyInterface.bootstrap("cast", "()", 0, callSite85);
        CallSite callSite86 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "otilde");
        otilde = IndyInterface.bootstrap("cast", "()", 0, callSite86);
        CallSite callSite87 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "ouml");
        ouml = IndyInterface.bootstrap("cast", "()", 0, callSite87);
        CallSite callSite88 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "divide");
        divide = IndyInterface.bootstrap("cast", "()", 0, callSite88);
        CallSite callSite89 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "oslash");
        oslash = IndyInterface.bootstrap("cast", "()", 0, callSite89);
        CallSite callSite90 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "ugrave");
        ugrave = IndyInterface.bootstrap("cast", "()", 0, callSite90);
        CallSite callSite91 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "uacute");
        uacute = IndyInterface.bootstrap("cast", "()", 0, callSite91);
        CallSite callSite92 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "ucirc");
        ucirc = IndyInterface.bootstrap("cast", "()", 0, callSite92);
        CallSite callSite93 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "uuml");
        uuml = IndyInterface.bootstrap("cast", "()", 0, callSite93);
        CallSite callSite94 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "yacute");
        yacute = IndyInterface.bootstrap("cast", "()", 0, callSite94);
        CallSite callSite95 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "thorn");
        thorn = IndyInterface.bootstrap("cast", "()", 0, callSite95);
        CallSite callSite96 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "yuml");
        yuml = IndyInterface.bootstrap("cast", "()", 0, callSite96);
        CallSite callSite97 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "lt");
        lt = IndyInterface.bootstrap("cast", "()", 0, callSite97);
        CallSite callSite98 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "gt");
        gt = IndyInterface.bootstrap("cast", "()", 0, callSite98);
        CallSite callSite99 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "amp");
        amp = IndyInterface.bootstrap("cast", "()", 0, callSite99);
        CallSite callSite100 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "apos");
        apos = IndyInterface.bootstrap("cast", "()", 0, callSite100);
        CallSite callSite101 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "quot");
        quot = IndyInterface.bootstrap("cast", "()", 0, callSite101);
        CallSite callSite102 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "OElig");
        OElig = IndyInterface.bootstrap("cast", "()", 0, callSite102);
        CallSite callSite103 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "oelig");
        oelig = IndyInterface.bootstrap("cast", "()", 0, callSite103);
        CallSite callSite104 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Scaron");
        Scaron = IndyInterface.bootstrap("cast", "()", 0, callSite104);
        CallSite callSite105 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "scaron");
        scaron = IndyInterface.bootstrap("cast", "()", 0, callSite105);
        CallSite callSite106 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Yuml");
        Yuml = IndyInterface.bootstrap("cast", "()", 0, callSite106);
        CallSite callSite107 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "circ");
        circ = IndyInterface.bootstrap("cast", "()", 0, callSite107);
        CallSite callSite108 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "tilde");
        tilde = IndyInterface.bootstrap("cast", "()", 0, callSite108);
        CallSite callSite109 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "ensp");
        ensp = IndyInterface.bootstrap("cast", "()", 0, callSite109);
        CallSite callSite110 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "emsp");
        emsp = IndyInterface.bootstrap("cast", "()", 0, callSite110);
        CallSite callSite111 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "thinsp");
        thinsp = IndyInterface.bootstrap("cast", "()", 0, callSite111);
        CallSite callSite112 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "zwnj");
        zwnj = IndyInterface.bootstrap("cast", "()", 0, callSite112);
        CallSite callSite113 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "zwj");
        zwj = IndyInterface.bootstrap("cast", "()", 0, callSite113);
        CallSite callSite114 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "lrm");
        lrm = IndyInterface.bootstrap("cast", "()", 0, callSite114);
        CallSite callSite115 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "rlm");
        rlm = IndyInterface.bootstrap("cast", "()", 0, callSite115);
        CallSite callSite116 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "ndash");
        ndash = IndyInterface.bootstrap("cast", "()", 0, callSite116);
        CallSite callSite117 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "mdash");
        mdash = IndyInterface.bootstrap("cast", "()", 0, callSite117);
        CallSite callSite118 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "lsquo");
        lsquo = IndyInterface.bootstrap("cast", "()", 0, callSite118);
        CallSite callSite119 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "rsquo");
        rsquo = IndyInterface.bootstrap("cast", "()", 0, callSite119);
        CallSite callSite120 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "sbquo");
        sbquo = IndyInterface.bootstrap("cast", "()", 0, callSite120);
        CallSite callSite121 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "ldquo");
        ldquo = IndyInterface.bootstrap("cast", "()", 0, callSite121);
        CallSite callSite122 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "rdquo");
        rdquo = IndyInterface.bootstrap("cast", "()", 0, callSite122);
        CallSite callSite123 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "bdquo");
        bdquo = IndyInterface.bootstrap("cast", "()", 0, callSite123);
        CallSite callSite124 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "dagger");
        dagger = IndyInterface.bootstrap("cast", "()", 0, callSite124);
        CallSite callSite125 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "Dagger");
        Dagger = IndyInterface.bootstrap("cast", "()", 0, callSite125);
        CallSite callSite126 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "permil");
        permil = IndyInterface.bootstrap("cast", "()", 0, callSite126);
        CallSite callSite127 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "lsaquo");
        lsaquo = IndyInterface.bootstrap("cast", "()", 0, callSite127);
        CallSite callSite128 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "rsaquo");
        rsaquo = IndyInterface.bootstrap("cast", "()", 0, callSite128);
        CallSite callSite129 = IndyInterface.bootstrap("init", "<init>", 0, Entity.class, "euro");
        euro = IndyInterface.bootstrap("cast", "()", 0, callSite129);
    }
}

