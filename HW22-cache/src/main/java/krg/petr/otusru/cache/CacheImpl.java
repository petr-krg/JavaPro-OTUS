package krg.petr.otusru.cache;


import java.util.HashSet;
import java.util.Set;
import java.util.WeakHashMap;

public class CacheImpl<K, V> implements Cache<K, V> {
//Надо реализовать эти методы

    private final WeakHashMap<K, V> map = new WeakHashMap<>();
    private final Set<Listener<K, V>> listenerSet = new HashSet<>();

    @Override
    public void put(K key, V value) {
        notifyListeners(key, value, "PUT");
        map.put(key, value);
    }

    @Override
    public void remove(K key) {
        V value = map.get(key);
        notifyListeners(key, value, "REMOVE");
        map.remove(key);
    }

    @Override
    public V get(K key) {
        V value = map.get(key);
        notifyListeners(key, value, "GET");
        return value;
    }

    @Override
    public void addListener(Listener<K, V> listener) {
        listenerSet.add(listener);
    }

    @Override
    public void removeListener(Listener<K, V> listener) {
        listenerSet.remove(listener);
    }

    private void notifyListeners(K key, V value, String action) {
        for (Listener<K, V> listener : listenerSet) {
            listener.notify(key, value, action);
        }
    }
}
