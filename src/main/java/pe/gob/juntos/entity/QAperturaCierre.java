package pe.gob.juntos.entity;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QAperturaCierre extends EntityPathBase<AperturaCierre> {
	
	private static final long serialVersionUID = -1761066074L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAperturaCierre aperturaCierre = new QAperturaCierre("aperturaCierre");
    
    public final QSede sede;
    
    public final QEspecifica especifica;
    
    public final NumberPath<Long> idAperturaCierre = createNumber("idAperturaCierre", Long.class);
    
    public final NumberPath<Long> anio = createNumber("anio", Long.class);
    
    public final NumberPath<Double> montoApertura = createNumber("montoApertura", Double.class);
    
    public final NumberPath<Double> montoLiquidacion = createNumber("montoLiquidacion", Double.class);
    
    public final NumberPath<Double> montoEjecucion = createNumber("montoEjecucion", Double.class);
    
    public final NumberPath<Double> saldo = createNumber("saldo", Double.class);

    public final StringPath flagCerrado = createString("flagCerrado");

    public final StringPath eliminado = createString("eliminado");

    //public final StringPath estado = createString("estado");

    public final DateTimePath<java.util.Date> fechaCreacion = createDateTime("fechaCreacion", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaModificacion = createDateTime("fechaModificacion", java.util.Date.class);

    public final StringPath usuarioCreacion = createString("usuarioCreacion");

    public final StringPath usuarioModificacion = createString("usuarioModificacion");

    public final StringPath vigente = createString("vigente");

    
    
    
    public QAperturaCierre(String variable) {
        this(AperturaCierre.class, forVariable(variable), INITS);
    }

    public QAperturaCierre(Path<? extends AperturaCierre> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAperturaCierre(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAperturaCierre(PathMetadata metadata, PathInits inits) {
        this(AperturaCierre.class, metadata, inits);
    }

    public QAperturaCierre(Class<? extends AperturaCierre> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        //this.sede = inits.isInitialized("sede") ? new QSede(forProperty("sede")) : null;
        this.sede = inits.isInitialized("sede") ? new QSede(forProperty("sede"), inits.get("sede")) : null;
        this.especifica = inits.isInitialized("especifica") ? new QEspecifica(forProperty("especifica")) : null;
        //this.administrado = inits.isInitialized("administrado") ? new QAdministrado(forProperty("administrado"), inits.get("administrado")) : null;
        //this.rendicionCajachica = inits.isInitialized("rendicionCajachica") ? new QRendicionCajaChica(forProperty("rendicionCajachica")) : null;
    }
    
}

