package me.monkey.demo.pattern.wrapperOrDecorator;

import org.springframework.data.geo.Point;

import java.util.*;

public class MyArrays {
    public List<Point> asList(Point[] points) {
        List<Point> list = new ArrayList<Point>();
        for (int i = 0; i < points.length; i++) {
            list.add(points[i]);
        }
        
        return new ArrayListWrapper(list);
    }
}

class ArrayListWrapper implements List<Point> {
    private List<Point> list;

    ArrayListWrapper(List<Point> list) {
        this.list = list;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Point> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Point e) {
        throw new UnsupportedOperationException("...");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("...");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Point> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Point> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Point get(int index) {
        return null;
    }

    @Override
    public Point set(int index, Point element) {
        return null;
    }

    @Override
    public void add(int index, Point element) {

    }

    @Override
    public Point remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<Point> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Point> listIterator(int index) {
        return null;
    }

    @Override
    public List<Point> subList(int fromIndex, int toIndex) {
        return null;
    }


}
