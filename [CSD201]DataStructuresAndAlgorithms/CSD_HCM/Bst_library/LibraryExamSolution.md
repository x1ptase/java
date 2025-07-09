# HƯỚNG DẪN GIẢI ĐỀ THI THỰC HÀNH QUẢN LÝ THƯ VIỆN

## Phần 1: Phân tích đề bài
Bài thi yêu cầu hoàn thiện một hệ thống quản lý thư viện sử dụng cấu trúc dữ liệu BST và Queue. Hai cấu trúc dữ liệu này được sử dụng cho hai mục đích khác nhau:
1. **BST (Binary Search Tree)**: Lưu trữ thông tin về sách, sắp xếp theo mã sách (code)
2. **Queue**: Quản lý danh sách các yêu cầu mượn sách theo thứ tự FIFO

## Phần 2: Các hàm cần hoàn thiện

### 1. Hàm thêm sách vào BST
```java
void addBook(String code, String title, int quantity, int category) {
    // Không thêm sách nếu quantity hoặc category <= 0
    if (quantity <= 0 || category <= 0) return;
    
    Book book = new Book(code, title, quantity, category);
    
    // Nếu BST rỗng, tạo node gốc mới
    if (isEmpty()) {
        root = new Node(book);
        return;
    }
    
    // Tìm vị trí thích hợp để thêm sách
    Node parent = null;
    Node current = root;
    
    while (current != null) {
        // Nếu mã sách đã tồn tại, không thêm mới
        if (code.equals(current.info.getCode())) {
            return;
        }
        
        parent = current;
        // Di chuyển sang nhánh trái nếu mã sách nhỏ hơn
        if (code.compareTo(current.info.getCode()) < 0) {
            current = current.left;
        }
        // Di chuyển sang nhánh phải nếu mã sách lớn hơn
        else {
            current = current.right;
        }
    }
    
    // Thêm node mới vào vị trí thích hợp
    if (code.compareTo(parent.info.getCode()) < 0) {
        parent.left = new Node(book);
    } else {
        parent.right = new Node(book);
    }
}
```

### 2. Hàm xóa sách khỏi BST
```java
void remove(String code) {
    root = removeRecursive(root, code);
}

private Node removeRecursive(Node node, String code) {
    if (node == null) return null;
    
    // Tìm node cần xóa
    if (code.compareTo(node.info.getCode()) < 0) {
        node.left = removeRecursive(node.left, code);
    } else if (code.compareTo(node.info.getCode()) > 0) {
        node.right = removeRecursive(node.right, code);
    } else {
        // Node với một hoặc không có con
        if (node.left == null) {
            return node.right;
        } else if (node.right == null) {
            return node.left;
        }
        
        // Node có hai con
        // Tìm successor (node nhỏ nhất bên phải)
        Node successor = findMin(node.right);
        
        // Copy thông tin từ successor vào node hiện tại
        node.info = successor.info;
        
        // Xóa successor
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
```

### 3. Hàm tìm sách theo tiêu chí
```java
private Node findBestMatchBook(Node node, String title, int quantity, int minCategory) {
    if (node == null) return null;
    
    // Khởi tạo kết quả tốt nhất
    Node bestMatch = null;
    
    // Xử lý node hiện tại
    if (node.info.getTitle().equals(title) && node.info.getQuantity() >= quantity) {
        if (node.info.getCategory() < minCategory) {
            bestMatch = node;
            minCategory = node.info.getCategory();
        }
    }
    
    // Xử lý cây con bên trái
    Node leftResult = findBestMatchBook(node.left, title, quantity, minCategory);
    if (leftResult != null && leftResult.info.getCategory() < minCategory) {
        bestMatch = leftResult;
        minCategory = leftResult.info.getCategory();
    }
    
    // Xử lý cây con bên phải
    Node rightResult = findBestMatchBook(node.right, title, quantity, minCategory);
    if (rightResult != null && rightResult.info.getCategory() < minCategory) {
        bestMatch = rightResult;
        minCategory = rightResult.info.getCategory();
    }
    
    return bestMatch;
}
```

### 4. Hàm thêm yêu cầu mượn sách vào Queue
```java
void enQueue(String title, int quantity) {
    // Không thêm yêu cầu nếu quantity <= 0
    if (quantity <= 0) return;
    
    // Tạo node mới chứa thông tin yêu cầu mượn sách
    Node2 newNode = new Node2(new Book(title, quantity));
    
    // Nếu Queue rỗng, node mới là cả front và rear
    if (isEmpty()) {
        front = rear = newNode;
    }
    // Nếu Queue không rỗng, thêm node mới vào cuối
    else {
        rear.next = newNode;
        rear = newNode;
    }
}
```

### 5. Hàm lấy yêu cầu mượn sách từ Queue
```java
Book deQueue() {
    // Nếu Queue rỗng, trả về null
    if (isEmpty()) {
        return null;
    }
    
    // Lấy thông tin từ node đầu tiên
    Book tmp = front.info;
    
    // Di chuyển front sang node tiếp theo
    front = front.next;
    
    // Nếu Queue trở thành rỗng, cập nhật rear = null
    if (front == null) {
        rear = null;
    }
    
    return tmp;
}
```

### 6. Hàm xử lý yêu cầu mượn sách
```java
void borrow(Book request) {
    // Nếu request rỗng, không xử lý
    if (request == null) return;
    
    // Tìm sách phù hợp nhất (theo title và có category nhỏ nhất)
    Node bestMatch = findBestMatchBook(bst.root, request.getTitle(), request.getQuantity(), Integer.MAX_VALUE);
    
    // Nếu tìm thấy sách phù hợp, giảm số lượng sách
    if (bestMatch != null) {
        bestMatch.info.setQuantity(bestMatch.info.getQuantity() - request.getQuantity());
        
        // Nếu số lượng sách giảm xuống <= 0, xóa sách khỏi BST
        if (bestMatch.info.getQuantity() <= 0) {
            bst.remove(bestMatch.info.getCode());
        }
    }
}
```

### 7. Hàm đếm tổng số sách còn lại
```java
int countBooks(Node p) {
    // Nếu node rỗng, trả về 0
    if (p == null) return 0;
    
    // Đếm tổng số sách ở node hiện tại và tất cả con cháu
    return p.info.getQuantity() + countBooks(p.left) + countBooks(p.right);
}
```

## Phần 3: Giải chi tiết các chức năng

### 1. Chức năng f1(): Hiển thị dữ liệu
- Load dữ liệu từ file data.txt bằng cách gọi hàm `load(0)`
- Tạo file f1.txt bằng cách khởi tạo RandomAccessFile
- Gọi hàm `ftraverse(f)` để in thông tin BST và Queue ra file
- Đóng file

### 2. Chức năng f2(): Xử lý một yêu cầu mượn sách
- Load dữ liệu từ file data.txt bằng cách gọi hàm `load(0)`
- In trạng thái ban đầu của BST và Queue ra file f2.txt
- Lấy một yêu cầu mượn sách từ Queue bằng hàm `queue.deQueue()`
- Xử lý yêu cầu bằng hàm `borrow(request)`
- In trạng thái sau khi xử lý ra file f2.txt
- Đóng file

### 3. Chức năng f3(): Xử lý tất cả yêu cầu mượn sách
- Load dữ liệu từ file data.txt bằng cách gọi hàm `load(0)`
- In trạng thái ban đầu của BST và Queue ra file f3.txt
- Lấy và xử lý tất cả yêu cầu mượn sách từ Queue:
  ```java
  Book request = queue.deQueue();
  while (request != null) {
      borrow(request);
      request = queue.deQueue();
  }
  ```
- In trạng thái sau khi xử lý ra file f3.txt
- Đóng file

### 4. Chức năng f4(): Đếm tổng số sách còn lại
- Load dữ liệu từ file data.txt bằng cách gọi hàm `load(0)`
- In trạng thái ban đầu của BST và Queue ra file f4.txt
- Xử lý tất cả yêu cầu mượn sách và tính tổng số sách còn lại:
  ```java
  Book request = queue.deQueue();
  while (request != null) {
      borrow(request);
      request = queue.deQueue();
  }
  count = countBooks(bst.root);
  ```
- In trạng thái sau khi xử lý và tổng số sách còn lại ra file f4.txt
- Đóng file

## Phần 4: Các lưu ý quan trọng

1. **Tìm kiếm sách theo title**:
   - Khi xử lý yêu cầu mượn sách, cần tìm kiếm theo title chứ không phải code
   - Vì BST được tổ chức theo code, việc tìm kiếm theo title cần duyệt toàn bộ cây

2. **Chọn sách phù hợp nhất**:
   - Nếu có nhiều sách cùng title, chọn sách có category nhỏ nhất (tốt nhất)
   - Sử dụng phương thức `findBestMatchBook` để tìm sách phù hợp nhất

3. **Xử lý khi hết sách**:
   - Khi số lượng sách giảm xuống <= 0, cần xóa sách khỏi BST
   - Sử dụng phương thức `remove` để xóa sách theo code

4. **Đếm số sách**:
   - Đếm tổng số sách = tổng quantity của tất cả node trong BST
   - Sách đã bị xóa khỏi BST (quantity = 0) không được tính vào tổng số sách

5. **Thứ tự xử lý yêu cầu**:
   - Các yêu cầu mượn sách được xử lý theo thứ tự FIFO (First In First Out)
   - Sử dụng Queue để đảm bảo thứ tự xử lý đúng

6. **Định dạng output**:
   - Sử dụng hàm `ftraverse(f)` để in thông tin BST và Queue
   - Format book: `(code,title,quantity,category)`
   - Format request: `(title,quantity)` 