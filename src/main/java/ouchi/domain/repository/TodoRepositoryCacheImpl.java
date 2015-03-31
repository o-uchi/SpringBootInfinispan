package ouchi.domain.repository;

import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.Query;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.infinispan.Cache;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.query.CacheQuery;
import org.infinispan.query.Search;
import org.infinispan.query.SearchManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ouchi.domain.model.Todo;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

@Repository("todoCache")
@Slf4j
public class TodoRepositoryCacheImpl implements TodoRepository {

    @Inject private EmbeddedCacheManager embeddedCacheManager;
    private Cache<Long, Todo> todoCache;

    @Inject
    private TodoRepository todoRepository;

    @PostConstruct
    public void init() {
        System.out.println("init. todoCache");
        todoCache = embeddedCacheManager.getCache("todos");
//        todoCache = embeddedCacheManager.getCache("todos", true);
        todoRepository.findAll().forEach(this::save);
    }

    @Override
    public Todo save(Todo entity) {
        return todoCache.put(entity.getTodoId(), entity);
    }

    @Override
    public Todo findOne(Long aLong) {
        return todoCache.get(aLong);
    }

    @Override
    public boolean exists(Long aLong) {
        return todoCache.containsKey(aLong);
    }

    @Override
    public List<Todo> findAll() {
        return null;
    }

    @Override
    public List<Todo> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Todo> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Todo> findAll(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return todoCache.size();
    }

    @Override
    public void delete(Long aLong) {
        todoCache.remove(aLong);
    }

    @Override
    public void delete(Todo entity) {

    }

    @Override
    public void delete(Iterable<? extends Todo> entities) {

    }

    @Override
    public void deleteAll() {
        todoCache.clear();
    }

    @Override
    public void flush() {

    }

    @Override
    public void deleteInBatch(Iterable<Todo> entities) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Todo getOne(Long aLong) {
        return null;
    }

    @Override
    public Todo saveAndFlush(Todo entity) {
        return null;
    }

    @Override
    public List<Todo> save(Iterable entities) {
        return null;
    }

    @Override
    public List<Todo> searchTitleOrSummary(String keyword) {
        SearchManager manager =	Search.getSearchManager(todoCache);
        QueryBuilder builder = manager.buildQueryBuilderForClass(Todo.class).get();
        Query luceneQuery = builder.keyword()
                .onFields("todoTitle", "summary", "finished")
                .matching(keyword)
                .createQuery();
        log.info(luceneQuery.toString());
        CacheQuery query = manager.getQuery(luceneQuery, Todo.class);
        List list = query.list();
        return list;
    }
}
