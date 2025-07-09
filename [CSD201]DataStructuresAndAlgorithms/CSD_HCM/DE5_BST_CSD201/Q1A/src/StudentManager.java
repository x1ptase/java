class StudentBST {

    TreeNode root;

    StudentBST() {
        root = null;
    }

    boolean isEmpty() {
        return root == null;
    }

    void clear() {
        root = null;
    }

    TreeNode search(TreeNode p, String xId) {
        if (p == null) {
            return null;
        }
        if (p.info.getId().equals(xId)) {
            return p;
        }
        if (xId.compareTo(p.info.getId()) < 0) {
            return search(p.left, xId);
        } else {
            return search(p.right, xId);
        }
    }

    void insert(String id, String name, double gpa, String major, double balance) {
        // Implement this function - Insert a new student to the BST
        // If the ID already exists, update the student's information
        // --------------------------------------------------------
        Student x = new Student(id, name, gpa, major, balance);
        TreeNode q = new TreeNode(x);
        if (root == null) {
            root = q;
            return;
        }
        TreeNode f, p;
        p = root;
        f = null;
        while (p != null) {
            if (p.info.getId().equals(x.getId())) {
                // Nếu ID đã tồn tại, cập nhật thông tin sinh viên
                p.info.setName(name);
                p.info.setGpa(gpa);
                p.info.setMajor(major);
                p.info.setBalance(balance);
                return;
            }
            f = p;
            if (x.getId().compareTo(p.info.getId()) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (x.getId().compareTo(f.info.getId()) < 0) {
            f.left = q;
        } else {
            f.right = q;
        }
        // --------------------------------------------------------
    }

    // Find student with highest GPA
    Student findHighestGPA() {
        // Implement this function - Find student with highest GPA
        // --------------------------------------------------------
        if (root == null) {
            return null;
        }

        return findHighestGPA(root);
    }

    private Student findHighestGPA(TreeNode node) {
        if (node == null) {
            return null;
        }

        // Khởi tạo sinh viên có GPA cao nhất là sinh viên hiện tại
        Student highestGPAStudent = node.info;

        // Tìm sinh viên có GPA cao nhất ở cây con bên trái
        Student leftHighest = findHighestGPA(node.left);
        if (leftHighest != null && leftHighest.getGpa() > highestGPAStudent.getGpa()) {
            highestGPAStudent = leftHighest;
        }

        // Tìm sinh viên có GPA cao nhất ở cây con bên phải
        Student rightHighest = findHighestGPA(node.right);
        if (rightHighest != null && rightHighest.getGpa() > highestGPAStudent.getGpa()) {
            highestGPAStudent = rightHighest;
        }

        return highestGPAStudent;
    }

    // Count students with GPA greater than or equal to threshold
    int countByGPA(double threshold) {
        // Implement this function - Count students with GPA >= threshold
        // --------------------------------------------------------
        return countByGPA(root, threshold);
    }

    private int countByGPA(TreeNode node, double threshold) {
        if (node == null) {
            return 0;
        }

        int count = 0;
        // Kiểm tra nếu sinh viên hiện tại có GPA >= ngưỡng
        if (node.info.getGpa() >= threshold) {
            count = 1;
        }

        // Đệ quy đếm cả cây con bên trái và phải
        return count + countByGPA(node.left, threshold) + countByGPA(node.right, threshold);
    }

    // Count students by major
    int countByMajor(String major) {
        return countByMajor(root, major);
    }

    private int countByMajor(TreeNode p, String major) {
        if (p == null)
            return 0;

        int count = 0;
        if (p.info.getMajor().equals(major))
            count = 1;

        return count + countByMajor(p.left, major) + countByMajor(p.right, major);
    }

    // Update student balance in BST
    boolean updateStudentBalance(String id, double amount) {
        // Implement this function - Update balance of student with given ID
        // --------------------------------------------------------
        TreeNode node = search(root, id);
        if (node == null) {
            // Không tìm thấy sinh viên
            return false;
        }

        // Cộng thêm số tiền vào tài khoản sinh viên
        node.info.addToBalance(amount);
        return true;
        // --------------------------------------------------------
    }

    void loadDataStudents(int k) {
        try {
            String[] ids = Lib.readLineToStrArray("data.txt", k);
            String[] names = Lib.readLineToStrArray("data.txt", k + 1);
            double[] gpas = Lib.readLineToDoubleArray("data.txt", k + 2);
            String[] majors = Lib.readLineToStrArray("data.txt", k + 3);
            double[] balances = Lib.readLineToDoubleArray("data.txt", k + 4);

            int n = Math.min(ids.length, Math.min(names.length, Math.min(gpas.length,
                    Math.min(majors.length, balances.length))));

            for (int i = 0; i < n; i++) {
                insert(ids[i], names[i], gpas[i], majors[i], balances[i]);
            }
        } catch (Exception e) {
            System.out.println("Error loading student data: " + e);
        }
    }
}

// ScholarshipList.java
class ScholarshipList {

    ListNode head, tail;

    ScholarshipList() {
        head = tail = null;
    }

    boolean isEmpty() {
        return head == null;
    }

    void clear() {
        head = tail = null;
    }

    void addLast(String id, String name, double gpa, String major, double scholarshipAmount) {
        // Implement this function - add a new student to the end of the linked list
        // --------------------------------------------------------
        Student student = new Student(id, name, gpa, major, scholarshipAmount);
        ListNode newNode = new ListNode(student);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        // --------------------------------------------------------
    }

    ListNode search(String xId) {
        ListNode p = head;
        while (p != null) {
            if (p.info.getId().equals(xId)) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    // Remove a student from scholarship list by ID
    ListNode removeById(String id) {
        // Implement this function - Remove student with given ID from list
        // --------------------------------------------------------
        if (isEmpty()) {
            return null;
        }

        // Trường hợp xóa node đầu tiên
        if (head.info.getId().equals(id)) {
            ListNode removedNode = head;
            head = head.next;
            if (head == null) { // Nếu danh sách chỉ có 1 phần tử
                tail = null;
            }
            return removedNode;
        }

        // Tìm node cần xóa
        ListNode current = head;
        while (current.next != null && !current.next.info.getId().equals(id)) {
            current = current.next;
        }

        // Nếu không tìm thấy ID
        if (current.next == null) {
            return null;
        }

        // Lưu node cần xóa
        ListNode removedNode = current.next;

        // Xóa node khỏi danh sách
        current.next = current.next.next;

        // Nếu node bị xóa là tail, cập nhật tail
        if (removedNode == tail) {
            tail = current;
        }

        return removedNode;
        // --------------------------------------------------------
    }

    // Load initial scholarship data from data.txt file (from line k+5)
    void loadInitialScholarship(int k) {
        try {
            String[] ids = Lib.readLineToStrArray("data.txt", k + 5);
            String[] names = Lib.readLineToStrArray("data.txt", k + 6);
            double[] gpas = Lib.readLineToDoubleArray("data.txt", k + 7);
            String[] majors = Lib.readLineToStrArray("data.txt", k + 8);
            double[] amounts = Lib.readLineToDoubleArray("data.txt", k + 9);

            int n = Math.min(ids.length, Math.min(names.length,
                    Math.min(gpas.length, Math.min(majors.length, amounts.length))));

            for (int i = 0; i < n; i++) {
                addLast(ids[i], names[i], gpas[i], majors[i], amounts[i]);
            }
        } catch (Exception e) {
            System.out.println("Error loading initial scholarship data: " + e);
        }
    }
}

class StudentManager {

    StudentBST studentBST = new StudentBST();
    ScholarshipList scholarshipList = new ScholarshipList();

    StudentManager() {
    }

    void fvisit(TreeNode p, java.io.RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void preOrder(TreeNode p, java.io.RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        fvisit(p, f);
        preOrder(p.left, f);
        preOrder(p.right, f);
    }

    void inOrder(TreeNode p, java.io.RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        inOrder(p.left, f);
        fvisit(p, f);
        inOrder(p.right, f);
    }

    void ftraverse(java.io.RandomAccessFile f) throws Exception {
        f.writeBytes("Student BST (Inorder Traversal):\r\n");
        inOrder(studentBST.root, f);
        f.writeBytes("\r\n");
        f.writeBytes("Scholarship List:\r\n");
        ListNode p = scholarshipList.head;
        if (p == null) {
            f.writeBytes("Empty");
        }
        while (p != null) {
            f.writeBytes(p.info + " ");
            p = p.next;
        }
        f.writeBytes("\r\n");
    }

    void load(int k) throws Exception {
        studentBST.loadDataStudents(k);
        scholarshipList.loadInitialScholarship(k);
    }

    void f1() throws Exception {
        // Test insert() function in StudentBST and addLast() function in
        // ScholarshipList
        load(0);
        String fname = "f1.txt";
        java.io.File g123 = new java.io.File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        java.io.RandomAccessFile f = new java.io.RandomAccessFile(fname, "rw");

        ftraverse(f);
        f.close();
    }

    void f2() throws Exception {
        // Find and display student with highest GPA
        load(0);
        String fname = "f2.txt";
        java.io.File g123 = new java.io.File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        java.io.RandomAccessFile f = new java.io.RandomAccessFile(fname, "rw");

        ftraverse(f);

        // Find student with highest GPA
        // --------------------------------------------------------
        Student highestGPA = studentBST.findHighestGPA();
        f.writeBytes("\r\nStudent with highest GPA: " + highestGPA + "\r\n");
        // --------------------------------------------------------

        f.close();
    }

    void f3() throws Exception {
        // Count students with GPA >= 3.5
        load(0);
        String fname = "f3.txt";
        java.io.File g123 = new java.io.File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        java.io.RandomAccessFile f = new java.io.RandomAccessFile(fname, "rw");

        // Count students with GPA >= 3.5
        // --------------------------------------------------------
        int count = studentBST.countByGPA(3.5);
        f.writeBytes("" + count);
        // --------------------------------------------------------

        f.close();
    }

    void f4() throws Exception {
        // Apply scholarship funds to student accounts
        load(0);
        String fname = "f4.txt";
        java.io.File g123 = new java.io.File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        java.io.RandomAccessFile f = new java.io.RandomAccessFile(fname, "rw");

        ftraverse(f);

        // Process scholarships - remove from list and add to student balance
        // --------------------------------------------------------
        while (!scholarshipList.isEmpty()) {
            // Lấy sinh viên đầu tiên trong danh sách học bổng
            ListNode student = scholarshipList.head;
            String studentId = student.info.getId();
            double scholarshipAmount = student.info.getBalance();

            // Cập nhật số tiền trong tài khoản của sinh viên trong BST
            studentBST.updateStudentBalance(studentId, scholarshipAmount);

            // Xóa sinh viên khỏi danh sách học bổng
            scholarshipList.removeById(studentId);
        }
        // --------------------------------------------------------

        ftraverse(f);
        f.close();
    }
}