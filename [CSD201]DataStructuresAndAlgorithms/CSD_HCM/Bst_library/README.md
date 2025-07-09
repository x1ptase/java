# HƯỚNG DẪN GIẢI BÀI THI QUẢN LÝ THƯ VIỆN

## Các yêu cầu đặc biệt cần xử lý

1. **Tìm kiếm sách theo tên (title) thay vì mã sách (code)**
   - Duyệt toàn bộ cây BST để tìm sách có title khớp với yêu cầu
   - Ví dụ: Khi tìm sách "Java", phải duyệt qua tất cả các node để tìm sách có title là "Java"

2. **Chọn sách có danh mục tốt nhất khi có nhiều sách cùng tên**
   - Khi tìm thấy nhiều sách cùng tên, chọn sách có category nhỏ nhất
   - Ví dụ: Sách "Java" có ở B001 (category 1), B002 (category 2), B003 (category 3), chọn B001 vì category = 1 là nhỏ nhất

3. **Kiểm tra số lượng sách đủ trước khi cho mượn**
   - Chỉ cho mượn khi sách có quantity >= số lượng yêu cầu
   - Ví dụ: Yêu cầu mượn "Java" số lượng 4, nhưng sách B001 chỉ có 5 nên có thể cho mượn

4. **Xóa sách khi số lượng bằng 0**
   - Khi số lượng sách giảm xuống 0 sau khi mượn, xóa sách khỏi BST
   - Ví dụ: Sau khi mượn hết 1 quyển "Python" của B006, cần xóa B006 khỏi BST

5. **Bỏ qua yêu cầu không hợp lệ**
   - Yêu cầu mượn sách không tồn tại hoặc số lượng không đủ sẽ bị bỏ qua
   - Ví dụ: Yêu cầu mượn "NotExist" bị bỏ qua vì không có sách này

## Đáp án chuẩn cho các hàm chính

### 1. Hàm findBestMatchBook - Tìm sách phù hợp nhất

```java
Node findBestMatchBook(Node p, String title, int quantity, int bestCategory) {
    if (p == null) return null;
    
    // Tìm ở nhánh trái
    Node bestLeft = findBestMatchBook(p.left, title, quantity, bestCategory);
    
    // Kiểm tra node hiện tại
    Node best = null;
    if (p.info.getTitle().equals(title) && p.info.getQuantity() >= quantity) {
        if (p.info.getCategory() < bestCategory) {
            best = p;
            bestCategory = p.info.getCategory();
        }
    }
    
    // Tìm ở nhánh phải
    Node bestRight = findBestMatchBook(p.right, title, quantity, bestCategory);
    
    // So sánh kết quả từ nhánh trái, node hiện tại và nhánh phải
    if (best != null) {
        if (bestLeft != null && bestLeft.info.getCategory() < best.info.getCategory()) {
            best = bestLeft;
        }
        if (bestRight != null && bestRight.info.getCategory() < best.info.getCategory()) {
            best = bestRight;
        }
        return best;
    } else {
        if (bestLeft != null) {
            if (bestRight != null) {
                return (bestLeft.info.getCategory() <= bestRight.info.getCategory()) ? bestLeft : bestRight;
            }
            return bestLeft;
        }
        return bestRight;
    }
}
```

### 2. Hàm remove - Xóa sách khỏi BST

```java
Node removeRecursive(Node root, String code) {
    // Trường hợp cơ sở: Nếu cây rỗng
    if (root == null) return null;
    
    // Tìm node cần xóa
    if (code.compareTo(root.info.getCode()) < 0) {
        root.left = removeRecursive(root.left, code);
    } else if (code.compareTo(root.info.getCode()) > 0) {
        root.right = removeRecursive(root.right, code);
    } else {
        // Đã tìm thấy node cần xóa
        
        // Trường hợp 1: Node không có con hoặc chỉ có 1 con
        if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        }
        
        // Trường hợp 2: Node có 2 con
        // Tìm node nhỏ nhất ở cây con bên phải (successor)
        root.info = findMin(root.right).info;
        
        // Xóa successor
        root.right = removeRecursive(root.right, root.info.getCode());
    }
    
    return root;
}

Node findMin(Node root) {
    while (root.left != null) {
        root = root.left;
    }
    return root;
}

void remove(String code) {
    root = removeRecursive(root, code);
}
```

### 3. Hàm borrow - Mượn sách

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

### 4. Hàm countBooks - Đếm số sách còn lại

```java
int countBooks(Node p) {
    if (p == null) return 0;
    return p.info.getQuantity() + countBooks(p.left) + countBooks(p.right);
}
```

## Kết quả mong đợi

### f1.txt
Hiển thị dữ liệu ban đầu từ file data.txt, gồm danh sách sách trong BST và danh sách yêu cầu mượn trong Queue.

### f2.txt
Sau khi xử lý yêu cầu mượn đầu tiên (Java,2), số lượng sách Java có mã B001 sẽ giảm từ 5 xuống 3 vì có category nhỏ nhất (1).

### f3.txt
Sau khi xử lý tất cả yêu cầu mượn:
- Sách "Java" B001 giảm từ 5 xuống 3-4=-1, nên bị xóa khỏi BST
- Sách "CSHARP" B004 giảm từ 2 xuống 2-3=-1, nên bị xóa khỏi BST
- Sách "Python" B006 giảm từ 1 xuống 1-1=0, nên bị xóa khỏi BST
- Sách "DOTNET" B008 giảm từ 2 xuống 2-2=0, nên bị xóa khỏi BST
- Các yêu cầu mượn "Java" số lượng 4, "CSHARP" số lượng 5, "Ruby" số lượng 5, và "NotExist" số lượng 1 đều bị bỏ qua vì không thỏa mãn điều kiện

### f4.txt
Tổng số sách còn lại sau khi xử lý tất cả yêu cầu là: 3 (B002) + 4 (B003) + 3 (B005) + 6 (B007) + 5 (B009) + 7 (B010) = 28 