package ouchi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Indexed
public class Todo extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
//    @Field(analyze = Analyze.NO)
//    @DocumentId(name = "id")
    private Long todoId;
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String todoTitle;
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String summary;
    @Field(index = Index.YES, analyze = Analyze.NO, store = Store.NO)
    private boolean finished;
}
