
public class MyList {

    Node head, tail;

    // (1)
    MyList() {
        head = tail = null;

    }

    // (2)
    boolean isEmpty() {
        return (head == null);
    }

    // (3)
    void clear() {
        head = tail = null;

    }

    // (4)
    void addLast(Person x) {
        Node qNode = new Node(x);
        if (isEmpty()) {
            head = tail = qNode;
            return;
        }
        tail.next = qNode;
        tail = qNode;

    }

    // (5) display information of Node
    void visit(Node p) {
        if (p != null) {
            System.out.print(p.info);
        }
    }

    // (6) duyet tu dau den cuoi list
    void traverse() {
        Node currentNode = head;

        while (currentNode != null) {
            System.out.println(currentNode.info);
            currentNode = currentNode.next;
        }

    }

    // (7) optional
    void addMany(String[] array, int[] b) {
        int n, i;
        n = array.length;
        for (i = 0; i < n; i++) {
            addLast(new Person(array[i], b[i]));
        }
    }

    // (8)
    Node searchByName(String xName) {
        Node pNode = head;
        while (pNode != null) {
            if (pNode.info.equals(xName)) {
                return pNode;
            }
            pNode = pNode.next;
        }

        return null;
    }

    // (9)
    void addFirst(Person x) {
        Node currentNode = new Node(x);
        head = new Node(x, head);
        if (tail == null) {
            tail = currentNode;
        }
    }

    // (10)
    void insertAfter(Node q, Person x) {
        if (q == null) {
            return;

        }
        Node qNext = q.next;
        Node pNode = new Node(x, qNext);
        q.next = pNode;
        if (tail == q) { // insert sau Node tail
            // cap nhat lai tail
            tail = pNode;
        }

    }

    // (11)
    void insertBefore(Node q, Person x) {
        if (q == null) {
            return;
        }
        if (q == head) { // chen tuoc node Head
            // cap nhat lai head
            addFirst(x);
            return;
        }
        // tim node F truoc node q
        Node fNode = head;
        while (fNode != null && fNode.next != q) {
            fNode = fNode.next;
        }
        if (fNode == null) { // q khong co trong list
            return;

        }

        // insert after f
        insertAfter(fNode, x);

    }

    // (12)
    void remove(Node q) {
        if (q == null) {
            return;
        }
        if (q == head) { // xoa not dau tien
            removeFirst();
            return;

        }
        // tim node F truoc node q
        Node fNode = head;
        while (fNode != null && fNode.next != q) {
            fNode = fNode.next;
        }
        if (fNode == null) { // q khong co trong list
            return;

        }
        // xoa q khoi list
        fNode.next = q.next;
        if (fNode.next == null) {
            tail = null;
        }

    }
    
    void removeFirst() {
        if (isEmpty()) {
            return;
        }
        head = head.next;
        if (head == null) {
            tail = null;
        }

    }

    // (13)
    void remove(String xName) {
        Node pNode = searchByName(xName);
        remove(pNode);
    }

    // (14)
    void remove(int xAge) {
        Node pNode = searchByAge(xAge);
        remove(pNode);
    }

    Node searchByAge(int xAge) {
        Node pNode = head;
        while (pNode != null) {
            if (pNode.info.age == xAge) {
                return pNode;
            }
            pNode = pNode.next;
        }

        return null;
    }

    // (15) xoa tat ca phan tu co xAge
    void removeAll(int xAge) {
        Node pNode;
        while (true) {
            pNode = searchByAge(xAge);
            if (pNode == null) {
                break;
            }
            remove(pNode);
        }
    }

    // (16) tim Node o vi tri thu k
    Node pos(int k) {
        int count = 0;
        Node pNode = head;
        while (pNode != null) {
            if (count == k) {
                return pNode;

            }
            count++;
            pNode = pNode.next;
        }

        return (null);
    }

    // (17)
    void removePos(int k) {
        Node pNode = pos(k);
        remove(pNode);
    }

    // (18)
    void sortByName() {
        Node piNode, pjNode;
        Person temp;
        piNode = head;
        while (piNode != null) {
            pjNode = piNode.next;
            while (pjNode != null) {
                if (piNode.info.name.compareTo(pjNode.info.name) > 0) {
                    temp = piNode.info;
                    piNode.info = pjNode.info;
                    pjNode.info = temp;
                }
                pjNode = pjNode.next;
            }
            piNode = piNode.next;
        }

    }

    // (19)
    void sortByAge() {
        Node piNode, pjNode;
        Person temp;
        piNode = head;
        while (piNode != null) {
            pjNode = piNode.next;
            while (pjNode != null) {
                if (piNode.info.age > pjNode.info.age) {
                    temp = piNode.info;
                    piNode.info = pjNode.info;
                    pjNode.info = temp;
                }
                pjNode = pjNode.next;
            }
            piNode = piNode.next;
        }

    }

    // (20)
    int size() {
        int count = 0;
        Node pNode = head;
        while (pNode != null) {
            count++;
            pNode = pNode.next;
        }
        return count;
    }

    // (21)
    Person[] toArray() {
        int n, i;
        n = size();
        Person[] persons = new Person[n];
        Node pNode = head;
        i = 0;
        while (pNode != null) {
            persons[i] = new Person(pNode.info.name, pNode.info.age);
        }

        return (persons);
    }

    // (22)
    void reverse() {
        MyList tList = new MyList();
        Node pNode = head;
        while (pNode != null) {
            tList.addFirst(pNode.info);
            pNode = pNode.next;
        }
        head = tList.head;
        tail = tList.tail;
    }

    /**
     * cach lam reverse addlast xoa phan tu dau tien di lien tuc nhu vay
     */
    // (23) finding Node have max Age and return that Node
    Node findMaxAge() {
        Node maxNode = this.head;
        Node currentNode = this.head;
        // loop from head to tail
        while (currentNode != null) {
            // if the age of Node have value higher than maxNode then assign maxNode = that
            // Node
            if (currentNode.info.age > maxNode.info.age) {
                maxNode = currentNode;
            }
            currentNode = currentNode.next;

        }
        return (maxNode);
    }

    // (24)
    Node findMinAge() {
        Node minNode = this.head;
        Node currentNode = this.head;
        while (currentNode != null) {
            if (minNode.info.age > currentNode.info.age) {
                minNode = currentNode;
            }
            currentNode = currentNode.next;
        }

        return (minNode);
    }

    // (24)
    void setData(Node p, Person x) {
        if (p != null) {
            p.info = x;
        }
    }

    // (26)
    void sortByAge(int k, int h) {
        if (k >= h) {
            return;
        }
        if (k < 0) {
            k = 0;
        }
        int n = size();
        if (h > n - 1) {
            h = n - 1;
        }
        Node start = pos(k);
        Node end = pos(h + 1);
        Node pi, pj;
        Person temp;
        pi = start;
        while (pi != end) {
            pj = pi.next;
            while (pj != end) {
                if (pi.info.age > pj.info.age) {
                    temp = pi.info;
                    pi.info = pj.info;
                    pj.info = temp;
                }
                pj = pj.next;
            }
            pi = pi.next;
        }
    }

    // (27)
    void reverse(int k, int h) // reverse from k to h
    {
        if (k >= h) {
            return;
        }
        if (k < 0) {
            return;
        }
        int n = size();
        if (h > n - 1) {
            return;
        }
        Person[] persons = toArray();
        int i, j;
        Person temp;
        i = k;
        j = h;
        while (i < j) {
            temp = persons[i];
            persons[i] = persons[j];
            persons[j] = temp;
            i++;
            j--;
        }
        clear();
        for (i = 0; i < persons.length; i++) {
            addLast(persons[i]);

        }

    }
}
