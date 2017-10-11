package ru.job4j.nonBlocking;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class cash for store model.
 * @author atrifonov.
 * @since 11.10.2017.
 * @version 1.
 */
public class NonBlockingCache {
    /**
     * The store of model.
     */
    private final ConcurrentHashMap<Integer, Model> map = new ConcurrentHashMap<>();

    /**
     * Add model in cash.
     * @param model the model for adding.
     */
    public void add(Model model) {
        if(model != null) {
            map.putIfAbsent(model.getId(), model);
        }
    }

    /**
     * Update model.
     * @param newModel updating model.
     * @throws OptimisticException if version isn't equal
     */
    public void update(Model newModel) throws OptimisticException {
        if(newModel != null) {
            map.computeIfPresent(newModel.getId(), (k, v) -> {
                if(newModel.getVersion() != v.getVersion()) {
                    throw new OptimisticException("version isn't equal");
                } else {
                    newModel.setVersion(newModel.getVersion() + 1);
                    return newModel;
                }
            });
        }
    }

    /**
     * Remove model from cache.
     * @param deletedModel model for deleting.
     * @return model, if it is absent, else null.
     */
    public Model delete(Model deletedModel) {
        Model model = null;
        if(deletedModel != null) {
            model = map.remove(deletedModel.getId());
        }
        return model;
    }
}
