/**
 * ObjectList class contains methods for creating and operating on ObjectLists
 */
public class ObjectList implements ObjectListInterface {
    private ObjectListNode list;
    private ObjectListNode last;

    /** Constructs an empty list
     */
    public ObjectList() {
        list = null;
        last = null;
    }

    /** getFirstNode returns the first node in the list
     * @return first node
     */
    public ObjectListNode getFirstNode() {
        return list;
    }

    /** getLastNode returns the last node in the list
     * @return last node
     */
    public ObjectListNode getLastNode() {
        return last;
    }

    /** getFirst returns the first object in the list. Runtime error if list is null
     * @return first object
     */
    public Object getFirst() {
        if (list == null) {
            System.out.println("Runtime Error: getFirst()");
            System.exit(1);
        }
        return list.getInfo();
    }

    /** getLast returns the last object in the list. Runtime error if list is null
     * @return last object
     */
    public Object getLast() {
        if (list == null) {
            System.out.println("Runtime Error: getLast()");
            System.exit(1);
        }
        return last.getInfo();
    }

    /** addFirst adds an object to the front of a list
     * @param o object to be added
     */
    public void addFirst(Object o) {
        ObjectListNode p = new ObjectListNode(o, list);
        if (list == null)
            last = p;
        list = p;
    }

    /**addFirst adds a node to the front of the list. Runtime error if node is null
     * @param p node to be added
     */
    public void addFirst(ObjectListNode p) {
        if (p == null) {
            System.out.println("Runtime Error: addFirst()");
            System.exit(1);
        }
        p.setNext(list);
        if (list == null)
            last = p;
        list = p;
    }

    /** addLast adds an object to the end of the list
     * @param o object to be added
     */
    public void addLast(Object o) {
        ObjectListNode p = new ObjectListNode(o);
        if (list == null)
            list = p;
        else
            last.setNext(p);
        last = p;
    }

    /** addLast adds a node to the end of the list. Runtime error if node is null
     * @param p node to be added
     */
    public void addLast(ObjectListNode p) {
        if (p == null) {
            System.out.println("Runtime Error: addLast()");
            System.exit(1);
        }
        p.setNext(null);
        if (list == null)
            list = p;
        else
            last.setNext(p);
        last = p;
    }

    /** removeFirst removes the first object from the list. Runtime error if list is null
     * @return first node of list
     */
    public Object removeFirst() {
        if (list == null) {
            System.out.println("Runtime Error: removeFirst()");
            System.exit(1);
        }
        ObjectListNode p = list;
        list = p.getNext();
        if (list == null)
            last = null;
        return p.getInfo();
    }

    /** removeLast removes the last object from the list. Runtime error if list is null
     * @return last node of list
     */
    public Object removeLast() {
        if (list == null) {
            System.out.println("Runtime Error: removeLast()");
            System.exit(1);
        }
        ObjectListNode p = list;
        ObjectListNode q = null;
        while (p.getNext() != null) {
            q = p;
            p = p.getNext();
        }
        if (q == null) {
            list = null;
            last = null;
        } else {
            q.setNext(null);
            last = q;
        }
        return p.getInfo();
    }

    /** insertAfter inserts an object after the node referenced by p. Runtime error if list or node is null
     * @param p node before node to be inserted
     * @param o list operating on
     */
    public void insertAfter(ObjectListNode p, Object o) {
        if (list == null || p == null) {
            System.out.println("Runtime Error: insertAfter()");
            System.exit(1);
        }
        ObjectListNode q = new ObjectListNode(o, p.getNext());
        p.setNext(q);
        if (q.getNext() == null)
            last = q;
    }

    /** insertAfter inserts a node after the node referenced by p. Runtime error if list or nodes are null
     * @param p node before node to be inserted
     * @param q node to be inserted
     */
    public void insertAfter(ObjectListNode p, ObjectListNode q) {
        if (list == null || p == null || q == null) {
            System.out.println("Runtime Error: insertAfter()");
            System.exit(1);
        }
        q.setNext(p.getNext());
        p.setNext(q);
        if (last.getNext() != null)
            last = q;
    }

    /** deleteAfter deletes the node after the node referenced by p. Runtime error if list or nodes are null
     * @param p node before node to be deleted
     * @return deleted node
     */
    public Object deleteAfter(ObjectListNode p) {
        if (list == null || p == null || p.getNext() == null) {
            System.out.println("Runtime Error: deleteAfter()");
            System.exit(1);
        }
        ObjectListNode q = p.getNext();
        p.setNext(q.getNext());
        if (p.getNext() == null)
            last = p;
        return q.getInfo();
    }

    /** Inserts an item into its correct location within an ordered list
     * @param o object to be inserted
     */
    public void insert(Object o) {
        ObjectListNode p = list;
        ObjectListNode q = null;
        while (p != null && ((Comparable) o).compareTo(p.getInfo()) > 0) {
            q = p;
            p = p.getNext();
        }
        if (q == null)
            addFirst(o);
        else
            insertAfter(q, o);
    }

    /** Inserts a node into its correct location within an ordered list
     * @param r node to be inserted
     */
    public void insert(ObjectListNode r) {
        ObjectListNode p = list;
        ObjectListNode q = null;
        while (p != null &&
                ((Comparable) r.getInfo()).compareTo(p.getInfo()) > 0) {
            q = p;
            p = p.getNext();
        }
        if (q == null)
            addFirst(r);
        else
            insertAfter(q, r);
    }

    /** Removes the first occurrence of an item in a list
     * @param o object to be removed
     * @return null or removed node
     */
    public Object remove(Object o) {
        ObjectListNode p = list;
        ObjectListNode q = null;
        while (p != null && ((Comparable) o).compareTo(p.getInfo()) !=
                0) {
            q = p;
            p = p.getNext();
        }
        if (p == null)
            return null;
        else return q == null ? removeFirst() : deleteAfter(q);
    }

    /** contains returns true if the item is found in the list
     * @param o object to be checked for
     * @return if object is in the list
     */
    public boolean contains(Object o) {
        ObjectListNode p = list;
        while (p != null && ((Comparable) o).compareTo(p.getInfo()) !=
                0)
            p = p.getNext();
        return p != null;
    }


    /** Returns a reference to the node with the requested value. Returns null otherwise
     * @param o object to be selected
     * @return reference with value or null
     */
    public ObjectListNode select(Object o) {
        ObjectListNode p = list;
        while (p != null)
            if (((Comparable) o).compareTo(p.getInfo()) == 0)
                return p;
            else
                p = p.getNext();
        return null;
    }

    /** isEmpty determines whether or not a list is empty
     * @return if list is empty
     */
    public boolean isEmpty() {
        return list == null;
    }

    /** clear removes all elements from a list
     */
    public void clear() {
        list = null;
        last = null;
    }

    /** size returns the number of elements in the list
     * @return number of elements
     */
    public int size() {
        int count = 0;
        ObjectListNode p = list;
        while (p != null) {
            ++count;
            p = p.getNext();
        }
        return count;
    }

    /** copyList makes a copy of a list
     * @return copied list
     */
    public ObjectList copyList() {
        ObjectListNode p = null;
        ObjectListNode q = null; // to satisfy compiler;
        ObjectListNode r = list;

        if (isEmpty())
            return null;
        ObjectList newList = new ObjectList();
        while (r != null) {
            p = new ObjectListNode(r.getInfo());
            if (newList.isEmpty())
                newList.addFirst(p);
            else
                q.setNext(p);
            q = p;
            r = r.getNext();
        }
        newList.last = p;
        return newList;
    }

    /** Reverses a list
     */
    public void reverse() {
        ObjectListNode p = list;
        ObjectListNode q = null;
        ObjectListNode r;

        while (p != null) {
            r = q;
            q = p;
            p = p.getNext();
            q.setNext(r);
        }
        last = list;
        list = q;
    }
}

