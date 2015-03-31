package ouchi.domain.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ouchi.domain.model.Todo;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

@Repository
@Slf4j
public class TodoRepositoryImpl implements TodoSearchRepository {

    @PersistenceContext
    EntityManager entityManager;
    Future<?> creatingIndex;

    @Override
//    @Transactional(readOnly = true)
    public List<Todo> searchTitleOrSummary(String keyword) {
//        try {
//            creatingIndex.get();
//        } catch (InterruptedException e) {
//            log.warn("Interrupted!", e);
//            Thread.currentThread().interrupt();
//        } catch (ExecutionException e) {
//            log.error("Index creation failed!!", e);
//        }
//        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
//        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Todo.class).get();
//        org.apache.lucene.search.Query query = queryBuilder.keyword().onFields("todoTitle", "summary", "finished").matching(keyword).createQuery();
//        log.info(query.toString());
//        org.apache.lucene.search.Sort sort = new Sort(new SortField("lastModifiedDate", SortField.Type.STRING_VAL, true));
//        @SuppressWarnings("unchecked")
//        List<Todo> resultList = fullTextEntityManager.createFullTextQuery(query, Todo.class).setSort(sort).getResultList();
//        return resultList;
        return Collections.emptyList();
    }

    @PostConstruct
    public void doIndex() {
//        FullTextEntityManager fullTextEntityManager = Search
//                .getFullTextEntityManager(entityManager);
//        log.info("Create index...");
//        creatingIndex = fullTextEntityManager.createIndexer().start();
    }
}
