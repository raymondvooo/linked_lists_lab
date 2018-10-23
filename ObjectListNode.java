/**
 * ObjectListNode class contains methods for operating with ObjectLists and their nodes.
 */
public class ObjectListNode implements ObjectListNodeInterface {
    private Object info;
    private ObjectListNode next;

    /**
     * Default constructor
     */
    public ObjectListNode() {
        info = null;
        next = null;
    }

    /**
     * One arg constructor
     * @param o info of object of node
     */
    public ObjectListNode(Object o) {
        info = o;
        next = null;
    }

    /**
     * Two arg constructor
     * @param o info of object of node
     * @param p next node in the list
     */
    public ObjectListNode(Object o, ObjectListNode p) {
        info = o;
        next = p;
    }

    /**
     * setInfo sets the info field of node
     * @param o info to be passed in
     */
    public void setInfo(Object o) {
        info = o;
    }

    /** getInfo returns object in info field
     * @return info field
     */
    public Object getInfo() {
        return info;
    }

    /** setNext sets next field
     * @param p node to be set as next
     */
    public void setNext(ObjectListNode p) {
        next = p;
    }

    /** getNext returns next object in list
     * @return next object
     */
    public ObjectListNode getNext() {
        return next;
    }
}


