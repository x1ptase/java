import java.io.File;
import java.io.RandomAccessFile;

class BookBST {
    Node root;

    BookBST() {
        root = null;
    }

    boolean isEmpty() {
        return (root == null);
    }

    void clear() {
        root = null;
    }

    void loadDataBook(int k) // do not edit this function
    {
        String[] a = Lib.readLineToStrArray("data.txt", k);
        String[] b = Lib.readLineToStrArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int[] d = Lib.readLineToIntArray("data.txt", k + 3);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            addBook(a[i], b[i], c[i], d[i]);
        }
    }

    void addBook(String code, String title, int quantity, int category) {
        // Add book to BST. Don't add if quantity or category <= 0
        // Don't add if code already exists
        // --------------------------------------------------------
        if (quantity <= 0 || category <= 0)
            return;

        Book book = new Book(code, title, quantity, category);
        if (isEmpty()) {
            root = new Node(book);
            return;
        }

        Node parent = null;
        Node current = root;
        while (current != null) {
            if (code.equals(current.info.getCode())) {
                return; // Don't add if code already exists
            }
            parent = current;
            if (code.compareTo(current.info.getCode()) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (code.compareTo(parent.info.getCode()) < 0) {
            parent.left = new Node(book);
        } else {
            parent.right = new Node(book);
        }
        // ---------------------------------------------------------
    }

    // Search for book by code
    Node search(String code) {
        Node current = root;
        while (current != null) {
            if (code.equals(current.info.getCode())) {
                return current;
            }
            if (code.compareTo(current.info.getCode()) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    // Search for book by title
    Node searchByTitle(String title) {
        return searchByTitleRecursive(root, title);
    }

    private Node searchByTitleRecursive(Node node, String title) {
        if (node == null)
            return null;

        // Check current node
        if (title.equals(node.info.getTitle())) {
            return node;
        }

        // Search in left subtree
        Node leftResult = searchByTitleRecursive(node.left, title);
        if (leftResult != null)
            return leftResult;

        // Search in right subtree
        return searchByTitleRecursive(node.right, title);
    }

    // Search for a book with minimum price among books with available quantity
    Node findBookWithMinimumPrice(String title, int quantityNeeded) {
        return findBookWithCriteria(root, title, quantityNeeded);
    }

    private Node findBookWithCriteria(Node node, String title, int quantityNeeded) {
        if (node == null)
            return null;

        Node bestMatch = null;
        findBookWithCriteriaRecursive(node, title, quantityNeeded, bestMatch);
        return bestMatch;
    }

    private void findBookWithCriteriaRecursive(Node node, String title, int quantityNeeded, Node bestMatch) {
        if (node == null)
            return;

        // Process current node
        if (title.equals(node.info.getTitle()) && node.info.getQuantity() >= quantityNeeded) {
            if (bestMatch == null || node.info.getCategory() < bestMatch.info.getCategory()) {
                bestMatch = node;
            }
        }

        // Process left subtree
        findBookWithCriteriaRecursive(node.left, title, quantityNeeded, bestMatch);

        // Process right subtree
        findBookWithCriteriaRecursive(node.right, title, quantityNeeded, bestMatch);
    }

    // Remove a node from BST
    void remove(String code) {
        root = removeRecursive(root, code);
    }

    private Node removeRecursive(Node node, String code) {
        if (node == null)
            return null;

        // Navigate to the target node
        if (code.compareTo(node.info.getCode()) < 0) {
            node.left = removeRecursive(node.left, code);
        } else if (code.compareTo(node.info.getCode()) > 0) {
            node.right = removeRecursive(node.right, code);
        } else {
            // Node with only one child or no child
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // Node with two children
            // Find the inorder successor (smallest in the right subtree)
            Node successor = findMin(node.right);

            // Copy the successor's content to this node
            node.info = successor.info;

            // Delete the successor
            node.right = removeRecursive(node.right, successor.info.getCode());
        }

        return node;
    }

    private Node findMin(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Traverse tree in-order
    void inOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null)
            return;
        inOrder(p.left, f);
        f.writeBytes(p.info + " ");
        inOrder(p.right, f);
    }
}

class BorrowQueue {
    Node2 front, rear;

    BorrowQueue() {
        front = rear = null;
    }

    boolean isEmpty() {
        return (front == null);
    }

    void clear() {
        front = rear = null;
    }

    void loadDataRequest(int k) // do not edit this function
    {
        String[] a = Lib.readLineToStrArray("data.txt", k + 4);
        int[] b = Lib.readLineToIntArray("data.txt", k + 5);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            enQueue(a[i], b[i]);
        }
    }

    void enQueue(String title, int quantity) {
        // Add book borrow request to queue. Don't add if quantity <= 0
        // --------------------------------------------------------
        if (quantity <= 0)
            return;

        Node2 newNode = new Node2(new Book(title, quantity));
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        // ---------------------------------------------------------
    }

    Book deQueue() {
        // Remove and return the first request from queue
        // --------------------------------------------------------
        if (isEmpty()) {
            return null;
        }
        Book tmp = front.info;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        // ---------------------------------------------------------
        return tmp;
    }
}

class Library {
    BookBST bst = new BookBST();
    BorrowQueue queue = new BorrowQueue();

    Library() {
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void ftraverse(RandomAccessFile f) throws Exception {
        f.writeBytes("Book List: ");
        if (bst.isEmpty()) {
            f.writeBytes("Empty");
        } else {
            bst.inOrder(bst.root, f);
        }
        f.writeBytes("\r\n");
        f.writeBytes("Request  : ");
        Node2 p = queue.front;
        if (p == null) {
            f.writeBytes("Empty");
        }
        while (p != null) {
            f.writeBytes("(" + p.info.getTitle() + "," + p.info.getQuantity() + ") ");
            p = p.next;
        }
        f.writeBytes("\r\n");
    }

    void load(int k) throws Exception // do not edit this function
    {
        bst.loadDataBook(k);
        queue.loadDataRequest(k);
    }

    void f1() throws Exception {
        load(0);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        f.close();
    }

    void borrow(Book request) throws Exception {
        // Process a book borrow request
        // Find book by title in BST, decrease quantity if found and has enough quantity
        // If quantity becomes 0, consider removing the book from BST
        // --------------------------------------------------------
        if (request == null)
            return;

        // Traverse the BST to find matching books
        Node current = bst.root;
        Node bestMatch = null;
        int minCategory = Integer.MAX_VALUE;

        // Iterate through BST to find best matching book
        bestMatch = findBestMatchBook(bst.root, request.getTitle(), request.getQuantity(), minCategory);

        // If a suitable book is found, decrease its quantity
        if (bestMatch != null) {
            bestMatch.info.setQuantity(bestMatch.info.getQuantity() - request.getQuantity());

            // If quantity becomes 0, remove the book from BST
            if (bestMatch.info.getQuantity() <= 0) {
                bst.remove(bestMatch.info.getCode());
            }
        }
        // ---------------------------------------------------------
    }

    // Helper method to find the best matching book with minimum category
    private Node findBestMatchBook(Node node, String title, int quantity, int minCategory) {
        if (node == null)
            return null;

        // Process current node
        Node bestMatch = null;

        if (node.info.getTitle().equals(title) && node.info.getQuantity() >= quantity) {
            if (node.info.getCategory() < minCategory) {
                bestMatch = node;
                minCategory = node.info.getCategory();
            }
        }

        // Process left subtree
        Node leftResult = findBestMatchBook(node.left, title, quantity, minCategory);
        if (leftResult != null && leftResult.info.getCategory() < minCategory) {
            bestMatch = leftResult;
            minCategory = leftResult.info.getCategory();
        }

        // Process right subtree
        Node rightResult = findBestMatchBook(node.right, title, quantity, minCategory);
        if (rightResult != null && rightResult.info.getCategory() < minCategory) {
            bestMatch = rightResult;
            minCategory = rightResult.info.getCategory();
        }

        return bestMatch;
    }

    void f2() throws Exception {
        load(0);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        // Process the first borrow request in queue
        // --------------------------------------------------------
        Book request = queue.deQueue();
        borrow(request);
        // ---------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    void f3() throws Exception {
        load(0);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        // Process all borrow requests in queue
        // --------------------------------------------------------
        Book request = queue.deQueue();
        while (request != null) {
            borrow(request);
            request = queue.deQueue();
        }
        // ---------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    void f4() throws Exception {
        load(0);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        int count = 0;
        // Count total remaining books after processing all requests
        // --------------------------------------------------------
        Book request = queue.deQueue();
        while (request != null) {
            borrow(request);
            request = queue.deQueue();
        }

        // Count total books in BST
        count = countBooks(bst.root);
        // ---------------------------------------------------------
        ftraverse(f);
        f.writeBytes("Number of books remaining: " + count + " ");
        f.close();
    }

    int countBooks(Node p) {
        if (p == null)
            return 0;
        return p.info.getQuantity() + countBooks(p.left) + countBooks(p.right);
    }
}