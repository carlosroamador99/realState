package com.example.state4reals.model;

public class Categoria {
    private String type;

    public Categoria(){}

    public Categoria(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Categoria categoria = (Categoria) o;

        return type != null ? type.equals(categoria.type) : categoria.type == null;
    }

    @Override
    public int hashCode() {
        return type != null ? type.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "type='" + type + '\'' +
                '}';
    }
}
