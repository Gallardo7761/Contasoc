package dev.galliard.contasoc.common;

import java.util.Collection;
import java.util.Iterator;

public class Buffer<E> implements Iterable<E> {
    private Collection<E> data;

    public Buffer(Collection<E> data) {
        this.data = data;
    }

    public Buffer() {
        this.data = null;
    }

    public Collection<E> getData() {
        return data;
    }

    public void add(E e) {
        data.add(e);
    }

    public void remove(E e) {
        data.remove(e);
    }

    public void clear() {
        data.clear();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public int size() {
        return data.size();
    }

    public boolean contains(E e) {
        return data.contains(e);
    }

    public void addAll(Collection<E> c) {
        data.addAll(c);
    }

    public void removeAll(Collection<E> c) {
        data.removeAll(c);
    }

    public void update(E e, E newE) {
        data.remove(e);
        data.add(newE);
    }

    @Override
    public Iterator<E> iterator() {
        return data.iterator();
    }
}
