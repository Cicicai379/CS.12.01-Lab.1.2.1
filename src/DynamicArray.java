import java.lang.reflect.Array;

public class DynamicArray<a> {
    private a[] array;
    private int size;
    public DynamicArray(Class<a> type) {
        this.array = (a[]) Array.newInstance(type, 50);
        this.size = 0;
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public a get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return array[index];
    }

    public boolean contains(a element) {
        return indexOf(element) != -1;
    }

    public void add(a element) {
        if (size == array.length) {
            resizeArray();
        }
        array[size++] = element;
    }
    public void add(int index, a element) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Array Index out of Bounds!");
        }
        if (size == array.length) {
            resizeArray();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    public void set(int index, a element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Array Index out of Bounds!");
        }
        array[index] = element;
    }


    public a remove(a element) {
        int index = indexOf(element);
        if (index != -1) {
            remove(index);
        }
        return element;
    }

    public a remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Array Index out of Bounds!");
        }
        a removedElement = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return removedElement;
    }


    public a removeAll(a element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(array[i])) {
                remove(i);
                i--; // Decrement i to recheck the current index because the element at i+1 has shifted to i
            }
        }
        return element;
    }


    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }



    private void resizeArray() {
        int newCapacity = array.length * 2;
        a[] newArray = (a[]) Array.newInstance((Class<a>) array.getClass().getComponentType(), newCapacity);
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }
    private int indexOf(a element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }
}