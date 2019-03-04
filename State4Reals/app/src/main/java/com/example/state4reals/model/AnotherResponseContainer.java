package com.example.state4reals.model;


public class AnotherResponseContainer<T>{
    private T rows;
    private long count;

    public AnotherResponseContainer(){}

    public AnotherResponseContainer(T rows, long count) {
        this.rows = rows;
        this.count = count;
    }

    public T getRows() {
        return rows;
    }

    public void setRows(T rows) {
        this.rows = rows;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnotherResponseContainer<?> that = (AnotherResponseContainer<?>) o;

        if (count != that.count) return false;
        return rows != null ? rows.equals(that.rows) : that.rows == null;
    }

    @Override
    public int hashCode() {
        int result = rows != null ? rows.hashCode() : 0;
        result = 31 * result + (int) (count ^ (count >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "AnotherResponseContainer{" +
                "rows=" + rows +
                ", count=" + count +
                '}';
    }
}
