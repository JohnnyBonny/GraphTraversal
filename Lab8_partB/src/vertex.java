import java.util.Objects;

public class vertex {
    vertex parent;
    String name;
    int start;
    int end;

    public vertex() {
        this.name = " ";
        this.parent = null;
        this.start = 0;
        this.end = 0;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setParent(vertex parent) {
        this.parent = parent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public vertex getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        vertex vertex = (vertex) o;
        return start == vertex.start &&
                end == vertex.end &&
                Objects.equals(parent, vertex.parent) &&
                Objects.equals(name, vertex.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, name, start, end);
    }

    @Override
    public String toString() {
        return "{" +name +
                ", start=" + start +
                ", end=" + end +
                "} ";
    }
}

