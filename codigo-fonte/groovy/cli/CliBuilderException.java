/*
 * Decompiled with CFR 0.152.
 */
package groovy.cli;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.transform.Internal;
import java.beans.Transient;
import java.lang.invoke.MethodHandles;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;

public class CliBuilderException
extends RuntimeException
implements GroovyObject {
    private static final long serialVersionUID = 3996705753888714632L;
    private static /* synthetic */ long $const$0;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;

    @Generated
    public CliBuilderException() {
        MetaClass metaClass;
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    @Generated
    public CliBuilderException(String param0) {
        MetaClass metaClass;
        Object[] objectArray = new Object[]{ScriptBytecodeAdapter.createPojoWrapper(param0, String.class)};
        CliBuilderException cliBuilderException = this;
        switch (ScriptBytecodeAdapter.selectConstructorAndTransformArguments(objectArray, -1, RuntimeException.class)) {
            case -2020310112: {
                Object[] objectArray2 = objectArray;
                super((Throwable)((Object)IndyInterface.bootstrap("cast", "()", 0, objectArray[0])));
                break;
            }
            case -1428966913: {
                Object[] objectArray2 = objectArray;
                super((String)((Object)IndyInterface.bootstrap("cast", "()", 0, objectArray[0])));
                break;
            }
            case -947674026: {
                Object[] objectArray2 = objectArray;
                super((String)((Object)IndyInterface.bootstrap("cast", "()", 0, objectArray[0])), (Throwable)((Object)IndyInterface.bootstrap("cast", "()", 0, objectArray[1])), DefaultTypeTransformation.booleanUnbox(objectArray[2]), DefaultTypeTransformation.booleanUnbox(objectArray[3]));
                break;
            }
            case -255735978: {
                Object[] objectArray2 = objectArray;
                super((String)((Object)IndyInterface.bootstrap("cast", "()", 0, objectArray[0])), (Throwable)((Object)IndyInterface.bootstrap("cast", "()", 0, objectArray[1])));
                break;
            }
            case 39797: {
                Object[] objectArray2 = objectArray;
                super();
                break;
            }
            default: {
                throw new IllegalArgumentException("This class has been compiled with a super class which is binary incompatible with the current super class found on classpath. You should recompile this class with the new version.");
            }
        }
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    @Generated
    public CliBuilderException(String param0, Throwable param1) {
        super(param0, param1);
        MetaClass metaClass;
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    @Generated
    public CliBuilderException(Throwable param0) {
        MetaClass metaClass;
        Object[] objectArray = new Object[]{ScriptBytecodeAdapter.createPojoWrapper(param0, Throwable.class)};
        CliBuilderException cliBuilderException = this;
        switch (ScriptBytecodeAdapter.selectConstructorAndTransformArguments(objectArray, -1, RuntimeException.class)) {
            case -2020310112: {
                Object[] objectArray2 = objectArray;
                super((Throwable)((Object)IndyInterface.bootstrap("cast", "()", 0, objectArray[0])));
                break;
            }
            case -1428966913: {
                Object[] objectArray2 = objectArray;
                super((String)((Object)IndyInterface.bootstrap("cast", "()", 0, objectArray[0])));
                break;
            }
            case -947674026: {
                Object[] objectArray2 = objectArray;
                super((String)((Object)IndyInterface.bootstrap("cast", "()", 0, objectArray[0])), (Throwable)((Object)IndyInterface.bootstrap("cast", "()", 0, objectArray[1])), DefaultTypeTransformation.booleanUnbox(objectArray[2]), DefaultTypeTransformation.booleanUnbox(objectArray[3]));
                break;
            }
            case -255735978: {
                Object[] objectArray2 = objectArray;
                super((String)((Object)IndyInterface.bootstrap("cast", "()", 0, objectArray[0])), (Throwable)((Object)IndyInterface.bootstrap("cast", "()", 0, objectArray[1])));
                break;
            }
            case 39797: {
                Object[] objectArray2 = objectArray;
                super();
                break;
            }
            default: {
                throw new IllegalArgumentException("This class has been compiled with a super class which is binary incompatible with the current super class found on classpath. You should recompile this class with the new version.");
            }
        }
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    @Generated
    protected CliBuilderException(String param0, Throwable param1, boolean param2, boolean param3) {
        super(param0, param1, param2, param3);
        MetaClass metaClass;
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != CliBuilderException.class) {
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

    public static /* synthetic */ void __$swapInit() {
        long l;
        $const$0 = l = 3996705753888714632L;
    }

    static {
        CliBuilderException.__$swapInit();
    }
}

