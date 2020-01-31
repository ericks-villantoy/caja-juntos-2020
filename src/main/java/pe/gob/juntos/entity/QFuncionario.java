package pe.gob.juntos.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFuncionario is a Querydsl query type for Funcionario
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFuncionario extends EntityPathBase<Funcionario> {

    private static final long serialVersionUID = -323104228L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFuncionario funcionario = new QFuncionario("funcionario");

    public final StringPath apellidoMaterno = createString("apellidoMaterno");

    public final StringPath apellidoPaterno = createString("apellidoPaterno");

    public final QCargo cargo;

    public final StringPath eliminado = createString("eliminado");

    public final DateTimePath<java.util.Date> fechaCreacion = createDateTime("fechaCreacion", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaModificacion = createDateTime("fechaModificacion", java.util.Date.class);

    public final NumberPath<Long> idFuncionario = createNumber("idFuncionario", Long.class);

    public final StringPath nombre = createString("nombre");

    public final StringPath numeroDocumento = createString("numeroDocumento");

    public final StringPath tipoDocumento = createString("tipoDocumento");

    public final StringPath usuarioCreacion = createString("usuarioCreacion");

    public final StringPath usuarioModificacion = createString("usuarioModificacion");

    public final StringPath vigente = createString("vigente");

    public QFuncionario(String variable) {
        this(Funcionario.class, forVariable(variable), INITS);
    }

    public QFuncionario(Path<? extends Funcionario> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFuncionario(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFuncionario(PathMetadata metadata, PathInits inits) {
        this(Funcionario.class, metadata, inits);
    }

    public QFuncionario(Class<? extends Funcionario> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cargo = inits.isInitialized("cargo") ? new QCargo(forProperty("cargo")) : null;
    }

}

