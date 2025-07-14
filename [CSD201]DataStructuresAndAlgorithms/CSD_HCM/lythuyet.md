
# Giáo Trình Cấu Trúc Dữ Liệu và Giải Thuật (DSA) với Java

## Giới Thiệu


Hãy cùng bắt đầu hành trình khám phá các cấu trúc dữ liệu và giải thuật thú vị này!

---
## Chương 1: Node

### 1.1. Giới Thiệu về Node

**Node** là một đơn vị cơ bản nhất trong cấu trúc dữ liệu, giống như một viên gạch để xây dựng nên các cấu trúc phức tạp hơn. Node được sử dụng trong nhiều cấu trúc dữ liệu như danh sách liên kết, cây nhị phân, đồ thị,...

### 1.2. Cấu Trúc của Node

Một Node thường có 2 thành phần chính:

1. **Dữ liệu (Data)**: 
   - Có thể là bất kỳ kiểu dữ liệu nào (int, String, object,...)
   - Lưu trữ thông tin/giá trị của node

2. **Con trỏ/Tham chiếu (Reference)**:
   - Lưu địa chỉ của (các) node khác
   - Số lượng con trỏ phụ thuộc vào loại cấu trúc dữ liệu

### 1.3. Các Loại Node Phổ Biến
Các Thành Phần

- Room info: Đây là dữ liệu mà node lưu trữ. Trong trường hợp này, Room có thể là một lớp đại diện cho thông tin về một phòng nào đó.

- Node next: Đây là một tham chiếu (reference) đến node tiếp theo trong cấu trúc dữ liệu. Trường next cùng kiểu với lớp Node cho phép liên kết các node với nhau.

Các Constructor

- Node(Room x, Node p): Constructor này cho phép khởi tạo một node với dữ liệu x và tham chiếu đến node p.

- Node(Room x): Constructor này gọi constructor đầu tiên, thiết lập next bằng null. Điều này thường được sử dụng khi tạo một node mới mà chưa liên kết nó với bất kỳ node nào khác.


1. **Node trong Danh Sách Liên Kết Đơn:**
   ```java
    class Node {
        Room info; // Dữ liệu lưu trữ trong node
        Node next; // Tham chiếu đến node tiếp theo

        // Constructor có tham số x và p
        Node(Room x, Node p) {
            info = x;
            next = p;
        }

        // Constructor chỉ có tham số x, gọi constructor trên với p là null
        Node(Room x) {
            this(x, null);
        }
    }
   ```

- Liên hệ bài thi PE:
```
    void addLast(String code, int status, int size, int price) { 
        if(size > 0 && price > 0){
            Node p=new Node(new Room(code, status, size, price));
            if(isEmpty()){
                head=tail=p;
            } else{
                tail.next=p;
                tail=p;
            }
        }
}
```
### Giải Thích Chi Tiết

- **Kiểm Tra Điều Kiện:** Đầu tiên, hàm kiểm tra xem `size` và `price` có lớn hơn 0 không. Nếu không, node sẽ không được thêm vào danh sách.
  
- **Tạo Node Mới:** Nếu điều kiện thỏa mãn, một node mới được tạo ra với dữ liệu là một đối tượng `Room` mới. Tham số `next` của node mới được thiết lập là `null` bởi vì nó sẽ là node cuối cùng trong danh sách.

    ```java
    Node p=new Node(new Room(code, status, size, price));
    ```

- **Kiểm Tra Danh Sách Rỗng:** Nếu danh sách liên kết đơn đang rỗng (`isEmpty()` trả về `true`), node mới vừa tạo sẽ trở thành cả `head` và `tail` của danh sách.

    ```java
    if(isEmpty()){
        head=tail=p;
    }
    ```

- **Thêm Node vào Cuối Danh Sách:** Nếu danh sách không rỗng, node hiện tại ở cuối danh sách (`tail`) sẽ được liên kết với node mới bằng cách gán `tail.next = newNode`. Sau đó, `tail` được cập nhật để trỏ đến node mới.

    ```java
    else{
        tail.next=p;
        tail=p;
    }
    ```

### Hiểu Rõ Hơn về `tail.next=p`

Hãy tưởng tượng danh sách liên kết đơn như sau trước khi thêm node mới:

```
Head -> [Room1 | Next] -> [Room2 | Next] -> [Room3 | Next] -> NULL
```

- **Head:** Trỏ đến `Room1`.
- **Tail:** Trỏ đến `Room3`.
- **Room3.next:** Trỏ đến `NULL` vì nó là node cuối cùng.

Khi bạn gọi `addLast` để thêm `Room4`, quá trình sẽ như sau:

1. **Tạo Node Mới:** `Room4` được tạo và `Room4.next` được đặt là `NULL`.
  
2. **Liên Kết Node Cuối Cùng với Node Mới:**
    - `tail.next = newNode;` tức là `Room3.next = Room4;`
    - Bây giờ, danh sách trở thành:
      ```
      Head -> [Room1 | Next] -> [Room2 | Next] -> [Room3 | Next] -> [Room4 | Next] -> NULL
      ```
  
3. **Cập Nhật Tail:** `tail = newNode;` tức là `tail` bây giờ trỏ đến `Room4`.



---------------------------
2. **Node trong Danh Sách Liên Kết Đôi:**
   ```java
    class Node {
        Room info;
        Node prev;
        Node next;
        
        Node(Room x, Node p, Node n) {
            info = x;
            prev = p;
            next = n;
        }
        
        Node(Room x) {
            this(x, null, null);
        }
    }
   ```
3. **Node trong Cây Nhị Phân:**
   ```java
   class Node {
       Room info;
       Node left;
       Node right;
       
       Node(Room x, Node l, Node r) {
           info = x;
           left = l;
           right = r;
       }
       
       Node(Room x) {
           this(x, null, null);
       }
   }
   ```



## Chương 2: Stack

### 2.1. Giới Thiệu về Stack

**Stack** là một cấu trúc dữ liệu theo nguyên tắc **Last-In-First-Out (LIFO)**. Điều này có nghĩa là phần tử được thêm vào cuối cùng sẽ được lấy ra đầu tiên.

### 2.2. Các Phép Toán Cơ Bản

- **Push:** Thêm một phần tử vào đỉnh stack.
- **Pop:** Loại bỏ phần tử ở đỉnh stack.
- **Peek/Top:** Xem phần tử ở đỉnh stack mà không loại bỏ nó.
- **isEmpty:** Kiểm tra stack có rỗng không.

### 2.3. Cách Thức Hoạt Động

1. **Push:** Phần tử mới được thêm vào đầu tiên và trở thành phần tử ở đỉnh stack.
2. **Pop:** Phần tử ở đỉnh stack được loại bỏ và phần tử tiếp theo trở thành đỉnh mới.

### 2.4. Minh Họa

```
Stack:
|   |
| 3 | <- Top
| 2 |
| 1 |
|___|
```

- **Push 4:**
```
Stack:
|   |
| 4 | <- Top
| 3 |
| 2 |
| 1 |
|___|
```

- **Pop:**
```
Stack:
|   |
| 3 | <- Top
| 2 |
| 1 |
|___|
```

---

## Chương 3: Queue

### 3.1. Giới Thiệu về Queue

**Queue** là một cấu trúc dữ liệu theo nguyên tắc **First-In-First-Out (FIFO)**. Phần tử được thêm vào đầu tiên sẽ được lấy ra đầu tiên.

### 3.2. Các Phép Toán Cơ Bản

- **Enqueue:** Thêm một phần tử vào cuối queue.
- **Dequeue:** Loại bỏ phần tử ở đầu queue.
- **Front/Peek:** Xem phần tử ở đầu queue mà không loại bỏ nó.
- **isEmpty:** Kiểm tra queue có rỗng không.

### 3.3. Cách Thức Hoạt Động

1. **Enqueue:** Phần tử mới được thêm vào cuối queue.
2. **Dequeue:** Phần tử ở đầu queue được loại bỏ.

### 3.4. Minh Họa

```
Queue:
Front -> [1] [2] [3] -> Rear
```

- **Enqueue 4:**
```
Queue:
Front -> [1] [2] [3] [4] -> Rear
```

- **Dequeue:**
```
Queue:
Front -> [2] [3] [4] -> Rear
```

---
## Chương 4: Danh Sách Liên Kết Đơn (Singly Linked List)

### 4.1. Giới Thiệu

**Danh sách liên kết đơn** là một cấu trúc dữ liệu tuyến tính, trong đó mỗi phần tử (node) chứa dữ liệu và một tham chiếu đến node tiếp theo. Không giống như mảng, danh sách liên kết đơn không yêu cầu kích thước cố định và cho phép thêm hoặc xóa các phần tử một cách linh hoạt.

### 4.2. Thành Phần của Danh Sách Liên Kết Đơn

Một danh sách liên kết đơn bao gồm các node, mỗi node chứa hai phần chính:
- **Data (Dữ liệu):** Thông tin mà node lưu trữ.
- **Next (Tham chiếu tiếp theo):** Liên kết đến node tiếp theo trong danh sách.

### 4.3. Các Phép Toán Cơ Bản

- **Add Last (Thêm vào cuối):** Thêm một node mới vào cuối danh sách.
- **Remove First (Loại bỏ đầu):** Loại bỏ node ở đầu danh sách.
- **Traverse (Duyệt danh sách):** Duyệt qua tất cả các node trong danh sách để thực hiện các thao tác như in dữ liệu.

### 4.4. Cách Thức Hoạt Động

#### 4.4.1. Thêm Node vào Cuối Danh Sách (Add Last)

**Quy trình:**
1. **Tạo Node Mới:** Tạo một node mới chứa dữ liệu cần thêm và đặt `next` của nó là `null`.
2. **Kiểm Tra Danh Sách Rỗng:** Nếu danh sách rỗng, node mới trở thành node đầu (`head`).
3. **Tìm Node Cuối Cùng:** Nếu danh sách không rỗng, duyệt từ đầu đến cuối danh sách để tìm node cuối cùng.
4. **Liên Kết Node Cuối Cùng với Node Mới:** Cập nhật `next` của node cuối cùng để trỏ đến node mới.

**Ví Dụ Minh Họa:**

Giả sử danh sách hiện tại là:
```
Head -> [1 | Next] -> [2 | Next] -> [3 | Next] -> NULL
```
**Thêm Node 4 vào cuối danh sách:**
1. Tạo node mới: `[4 | null]`
2. Tìm node cuối cùng (`3`) và cập nhật `next` của nó:
```
Head -> [1 | Next] -> [2 | Next] -> [3 | Next] -> [4 | Next] -> NULL
```

#### 4.4.2. Loại Bỏ Node ở Đầu Danh Sách (Remove First)

**Quy trình:**
1. **Kiểm Tra Danh Sách Rỗng:** Nếu danh sách rỗng, không thực hiện gì.
2. **Cập Nhật Node Đầu:** Đặt node đầu mới bằng node tiếp theo của node đầu hiện tại.
3. **Giải Phóng Node Đầu Cũ:** Loại bỏ node đầu cũ khỏi danh sách.

**Ví Dụ Minh Họa:**

Giả sử danh sách hiện tại là:
```
Head -> [1 | Next] -> [2 | Next] -> [3 | Next] -> [4 | Next] -> NULL
```
**Loại bỏ node đầu (`1`):**
1. Cập nhật node đầu mới thành node `2`.
2. Loại bỏ node `1`:
```
Head -> [2 | Next] -> [3 | Next] -> [4 | Next] -> NULL
```

#### 4.4.3. Duyệt Danh Sách (Traverse)

**Quy trình:**
1. **Bắt Đầu Từ Node Đầu:** Khởi đầu từ node đầu (`head`).
2. **Duyệt Qua Từng Node:** Truy cập dữ liệu của node hiện tại và di chuyển đến node tiếp theo thông qua `next`.
3. **Kết Thúc Khi Đến NULL:** Dừng khi node hiện tại là `null`.

**Ví Dụ Minh Họa:**

Giả sử danh sách hiện tại là:
```
Head -> [2 | Next] -> [3 | Next] -> [4 | Next] -> NULL
```
**Duyệt danh sách:**
- Truy cập node `2`, in ra `2`.
- Di chuyển đến node `3`, in ra `3`.
- Di chuyển đến node `4`, in ra `4`.
- Đến `NULL`, dừng duyệt.

### 4.5. Ví Dụ Thực Tế

**Mô Phỏng Hệ Thống Quản Lý Khách Sạn:**
- Mỗi node trong danh sách đại diện cho một phòng trống.
- **Add Last:** Khi có phòng mới được dọn dẹp và sẵn sàng, thêm nó vào cuối danh sách.
- **Remove First:** Khi khách đặt phòng, lấy phòng đầu tiên trong danh sách (phòng sớm nhất được dọn dẹp) để gán cho khách.

**Minh Họa Quy Trình:**

1. **Khởi Tạo Danh Sách Rỗng:**
   ```
   Head -> NULL
   ```

2. **Thêm Các Phòng 101, 102, 103:**
   ```
   Head -> [101 | Next] -> [102 | Next] -> [103 | Next] -> NULL
   ```

3. **Khách Đặt Phòng (Remove First):**
   - Loại bỏ phòng 101:
   ```
   Head -> [102 | Next] -> [103 | Next] -> NULL
   ```

---

## Chương 5: Danh Sách Liên Kết Đôi (Doubly Linked List)

### 5.1. Giới Thiệu

**Danh sách liên kết đôi** mở rộng danh sách liên kết đơn bằng cách thêm một tham chiếu đến node trước đó (`prev`). Điều này giúp dễ dàng duyệt ngược danh sách và thực hiện các thao tác thêm hoặc loại bỏ node ở cả hai đầu hoặc giữa danh sách một cách hiệu quả hơn.

### 5.2. Thành Phần của Danh Sách Liên Kết Đôi

Mỗi node trong danh sách liên kết đôi bao gồm:
- **Data (Dữ liệu):** Thông tin mà node lưu trữ.
- **Next (Tham chiếu tiếp theo):** Liên kết đến node tiếp theo trong danh sách.
- **Prev (Tham chiếu trước đó):** Liên kết đến node trước đó trong danh sách.

### 5.3. Các Phép Toán Cơ Bản

- **Add Last (Thêm vào cuối):** Thêm một node mới vào cuối danh sách.
- **Add First (Thêm vào đầu):** Thêm một node mới vào đầu danh sách.
- **Remove Last (Loại bỏ cuối):** Loại bỏ node ở cuối danh sách.
- **Remove First (Loại bỏ đầu):** Loại bỏ node ở đầu danh sách.
- **Traverse Forward (Duyệt tiến):** Duyệt từ đầu đến cuối danh sách.
- **Traverse Backward (Duyệt lùi):** Duyệt từ cuối đến đầu danh sách.

### 5.4. Cách Thức Hoạt Động

#### 5.4.1. Thêm Node vào Cuối Danh Sách (Add Last)

**Quy trình:**
1. **Tạo Node Mới:** Tạo một node mới chứa dữ liệu cần thêm, đặt `next` của nó là `null` và `prev` là node cuối cùng hiện tại.
2. **Kiểm Tra Danh Sách Rỗng:** Nếu danh sách rỗng, node mới trở thành node đầu (`head`) và node cuối (`tail`).
3. **Liên Kết Node Cuối Cùng với Node Mới:** Cập nhật `next` của node cuối cùng để trỏ đến node mới và `prev` của node mới trỏ đến node cuối cùng.

**Ví Dụ Minh Họa:**

Giả sử danh sách hiện tại là:
```
Head <-> [1 | Prev: null, Next: 2] <-> [2 | Prev: 1, Next: 3] <-> [3 | Prev: 2, Next: null] <-> NULL
```
**Thêm Node 4 vào cuối danh sách:**
1. Tạo node mới: `[4 | Prev: 3, Next: null]`
2. Cập nhật node cuối cùng (`3`) để trỏ đến node mới:
```
Head <-> [1 | Prev: null, Next: 2] <-> [2 | Prev: 1, Next: 3] <-> [3 | Prev: 2, Next: 4] <-> [4 | Prev: 3, Next: null] <-> NULL
```

#### 5.4.2. Thêm Node vào Đầu Danh Sách (Add First)

**Quy trình:**
1. **Tạo Node Mới:** Tạo một node mới chứa dữ liệu cần thêm, đặt `next` của nó trỏ đến node đầu hiện tại và `prev` là `null`.
2. **Kiểm Tra Danh Sách Rỗng:** Nếu danh sách rỗng, node mới trở thành node đầu và node cuối.
3. **Liên Kết Node Đầu Cũ với Node Mới:** Cập nhật `prev` của node đầu cũ để trỏ về node mới và cập nhật node đầu (`head`) thành node mới.

**Ví Dụ Minh Họa:**

Giả sử danh sách hiện tại là:
```
Head <-> [2 | Prev: null, Next: 3] <-> [3 | Prev: 2, Next: null] <-> NULL
```
**Thêm Node 1 vào đầu danh sách:**
1. Tạo node mới: `[1 | Prev: null, Next: 2]`
2. Cập nhật node đầu hiện tại (`2`) để trỏ `prev` đến node mới:
```
Head <-> [1 | Prev: null, Next: 2] <-> [2 | Prev: 1, Next: 3] <-> [3 | Prev: 2, Next: null] <-> NULL
```

#### 5.4.3. Loại Bỏ Node ở Cuối Danh Sách (Remove Last)

**Quy trình:**
1. **Kiểm Tra Danh Sách Rỗng:** Nếu danh sách rỗng, không thực hiện gì.
2. **Cập Nhật Node Cuối Cùng Mới:** Đặt node cuối mới bằng node `prev` của node cuối hiện tại.
3. **Giải Phóng Node Cuối Cũ:** Loại bỏ node cuối cũ bằng cách đặt `next` của node cuối mới thành `null`.

**Ví Dụ Minh Họa:**

Giả sử danh sách hiện tại là:
```
Head <-> [1 | Prev: null, Next: 2] <-> [2 | Prev: 1, Next: 3] <-> [3 | Prev: 2, Next: 4] <-> [4 | Prev: 3, Next: null] <-> NULL
```
**Loại bỏ node cuối (`4`):**
1. Cập nhật node cuối mới thành node `3`.
2. Đặt `next` của node `3` thành `null`:
```
Head <-> [1 | Prev: null, Next: 2] <-> [2 | Prev: 1, Next: 3] <-> [3 | Prev: 2, Next: null] <-> NULL
```

#### 5.4.4. Loại Bỏ Node ở Đầu Danh Sách (Remove First)

**Quy trình:**
1. **Kiểm Tra Danh Sách Rỗng:** Nếu danh sách rỗng, không thực hiện gì.
2. **Cập Nhật Node Đầu Mới:** Đặt node đầu mới bằng node `next` của node đầu hiện tại.
3. **Giải Phóng Node Đầu Cũ:** Loại bỏ node đầu cũ bằng cách đặt `prev` của node đầu mới thành `null`.

**Ví Dụ Minh Họa:**

Giả sử danh sách hiện tại là:
```
Head <-> [1 | Prev: null, Next: 2] <-> [2 | Prev: 1, Next: 3] <-> [3 | Prev: 2, Next: null] <-> NULL
```
**Loại bỏ node đầu (`1`):**
1. Cập nhật node đầu mới thành node `2`.
2. Đặt `prev` của node `2` thành `null`:
```
Head <-> [2 | Prev: null, Next: 3] <-> [3 | Prev: 2, Next: null] <-> NULL
```

#### 5.4.5. Duyệt Danh Sách Tiến và Lùi

**Duyệt Tiến (Forward Traversal):**
- Bắt đầu từ node đầu (`head`).
- Duyệt qua từng node bằng cách theo `next` và thực hiện các thao tác như in dữ liệu.

**Duyệt Lùi (Backward Traversal):**
- Bắt đầu từ node cuối (`tail`).
- Duyệt qua từng node bằng cách theo `prev` và thực hiện các thao tác như in dữ liệu.

**Ví Dụ Minh Họa:**

Giả sử danh sách hiện tại là:
```
Head <-> [2 | Prev: null, Next: 3] <-> [3 | Prev: 2, Next: 4] <-> [4 | Prev: 3, Next: null] <-> NULL
```

- **Duyệt Tiến:**
  ```
  2 -> 3 -> 4
  ```
- **Duyệt Lùi:**
  ```
  4 -> 3 -> 2
  ```

### 5.5. Ví Dụ Thực Tế

**Mô Phỏng Hệ Thống Xếp Hàng Cửa Hàng:**
- **Danh sách liên kết đôi** có thể được sử dụng để quản lý hàng đợi khách hàng, cho phép dễ dàng thêm khách hàng vào cuối hàng và loại bỏ khách hàng từ đầu hàng, cũng như duyệt ngược để xem các khách hàng gần nhất.

**Minh Họa Quy Trình:**

1. **Khởi Tạo Danh Sách Rỗng:**
   ```
   Head <-> NULL
   Tail <-> NULL
   ```

2. **Thêm Khách Hàng vào Cuối (Add Last):**
   - **Thêm "Alice":**
     ```
     Head <-> [Alice | Prev: null, Next: null] <-> Tail
     ```
   - **Thêm "Bob":**
     ```
     Head <-> [Alice | Prev: null, Next: Bob] <-> [Bob | Prev: Alice, Next: null] <-> Tail
     ```
   - **Thêm "Charlie":**
     ```
     Head <-> [Alice | Prev: null, Next: Bob] <-> [Bob | Prev: Alice, Next: Charlie] <-> [Charlie | Prev: Bob, Next: null] <-> Tail
     ```

3. **Loại Bỏ Khách Hàng từ Đầu (Remove First):**
   - **Loại bỏ "Alice":**
     ```
     Head <-> [Bob | Prev: null, Next: Charlie] <-> [Charlie | Prev: Bob, Next: null] <-> Tail
     ```

4. **Duyệt Ngược để Xem Khách Hàng Gần Nhất:**
   - **Duyệt Lùi:**
     ```
     Charlie -> Bob
     ```

### 5.6. Lợi Ích và Nhược Điểm của Danh Sách Liên Kết Đôi

**Lợi Ích:**
- **Duyệt Ngược Dễ Dàng:** Cho phép duyệt từ cuối đến đầu danh sách một cách thuận tiện.
- **Thêm và Xóa Phần Tử ở Cả Hai Đầu:** Có thể thực hiện các thao tác thêm và xóa ở đầu và cuối danh sách hiệu quả hơn.
- **Tính Linh Hoạt Cao:** Tương tự như danh sách liên kết đơn nhưng với khả năng linh hoạt mở rộng hơn.

**Nhược Điểm:**
- **Chi Phí Bộ Nhớ Cao Hơn:** Mỗi node cần thêm một tham chiếu (`prev`), làm tăng chi phí bộ nhớ.
- **Phức Tạp trong Quản Lý Tham Chiếu:** Việc thêm và loại bỏ node cần cập nhật cả hai tham chiếu `next` và `prev`, có thể dẫn đến lỗi nếu không được thực hiện chính xác.

### 5.7. So Sánh Danh Sách Liên Kết Đơn và Đôi

| Tiêu Chí                    | Danh Sách Liên Kết Đơn              | Danh Sách Liên Kết Đôi               |
|-----------------------------|-------------------------------------|--------------------------------------|
| **Số Tham Chiếu mỗi Node**  | 1 (`next`)                          | 2 (`next` và `prev`)                 |
| **Duyệt Ngược**            | Không dễ dàng                       | Dễ dàng duyệt từ cuối đến đầu        |
| **Thêm/Loại Bỏ ở Cuối**     | Cần duyệt hết danh sách             | Có thể duyệt tới cuối nhanh hơn nếu có `tail` |
| **Chi Phí Bộ Nhớ**          | Thấp hơn                             | Cao hơn do thêm tham chiếu `prev`    |
| **Ứng Dụng**                | Tốt cho các thao tác thêm/xóa đơn giản | Tốt cho các thao tác cần duyệt ngược hoặc thêm/xóa ở cả hai đầu |

---

## Chương 6: Cây Tìm Kiếm Nhị Phân (Binary Search Tree - BST)

### 6.1. Giới Thiệu

**Cây tìm kiếm nhị phân (BST)** là một cấu trúc dữ liệu cây, trong đó mỗi node có tối đa hai con, và giá trị của node bên trái luôn nhỏ hơn node cha, trong khi giá trị của node bên phải luôn lớn hơn node cha.

### 6.2. Các Phép Toán Cơ Bản

- **Insert:** Thêm một node mới vào cây.
- **Search:** Tìm kiếm một giá trị trong cây.
- **Delete:** Loại bỏ một node khỏi cây.
- **Traverse:** Duyệt cây theo các thứ tự khác nhau.

### 6.3. Cách Thức Hoạt Động

1. **Insert:** So sánh giá trị cần thêm với node hiện tại và di chuyển xuống cây con tương ứng (trái hoặc phải).
2. **Search:** Tương tự như insert, so sánh giá trị và di chuyển xuống cây con.
3. **Delete:** Có ba trường hợp: node không có con, node có một con, node có hai con.

### 6.4. Minh Họa

```
       10
      /  \
     5    15
    / \     \
   3   7     20
```

### 6.5. Tạo Cây Tìm Kiếm Nhị Phân (Creating a BST)

#### 6.5.1. Khái Niệm Cơ Bản

Để tạo một BST, bạn bắt đầu với một node gốc (root). Mỗi khi thêm một node mới, bạn so sánh giá trị của nó với các node hiện có trong cây và quyết định vị trí thích hợp (bên trái hoặc bên phải) dựa trên quy tắc BST.

#### 6.5.2. Quy Trình Tạo BST

1. **Khởi Tạo Cây:**
   - Bắt đầu với cây rỗng.
   - Khi thêm phần tử đầu tiên, nó trở thành node gốc của cây.

2. **Chèn Node Mới:**
   - So sánh giá trị của node mới với node hiện tại.
   - Nếu nhỏ hơn, di chuyển sang cây con bên trái.
   - Nếu lớn hơn, di chuyển sang cây con bên phải.
   - Lặp lại quá trình cho đến khi tìm được vị trí thích hợp (node con trống).

#### 6.5.3. Minh Họa Quy Trình Chèn

Giả sử chúng ta muốn tạo BST từ dãy số sau: 10, 5, 15, 3, 7, 20.

1. **Chèn 10:**
   ```
       10
   ```

2. **Chèn 5:**
   - 5 < 10 → Chèn bên trái của 10
   ```
       10
      /
     5
   ```

3. **Chèn 15:**
   - 15 > 10 → Chèn bên phải của 10
   ```
       10
      /  \
     5    15
   ```

4. **Chèn 3:**
   - 3 < 10 → sang trái 10
   - 3 < 5 → chèn bên trái của 5
   ```
       10
      /  \
     5    15
    /
   3
   ```

5. **Chèn 7:**
   - 7 < 10 → sang trái 10
   - 7 > 5 → chèn bên phải của 5
   ```
       10
      /  \
     5    15
    / \
   3   7
   ```

6. **Chèn 20:**
   - 20 > 10 → sang phải 10
   - 20 > 15 → chèn bên phải của 15
   ```
       10
      /  \
     5    15
    / \     \
   3   7     20
   ```

### 6.6. Thêm Node vào BST (Insert)

#### 6.6.1. Quy Trình Chèn Node Mới

Khi thêm một node mới vào BST, quy trình như sau:

1. **Bắt Đầu Từ Node Gốc:**
   - So sánh giá trị của node mới với node hiện tại.

2. **Di Chuyển Xuống Cây Con:**
   - Nếu giá trị nhỏ hơn node hiện tại, di chuyển sang cây con bên trái.
   - Nếu lớn hơn, di chuyển sang cây con bên phải.

3. **Chèn Node Khi Tìm Được Vị Trí:**
   - Khi gặp node con trống (`null`), chèn node mới vào vị trí đó.

#### 6.6.2. Minh Họa Chèn Node

Giả sử chúng ta có BST hiện tại:
```
       10
      /  \
     5    15
    / \     \
   3   7     20
```
- **Chèn 8:**
  - 8 < 10 → sang trái 10
  - 8 > 5 → sang phải 5
  - 8 > 7 → chèn bên phải của 7
```
       10
      /  \
     5    15
    / \     \
   3   7     20
         \
          8
```

### 6.7. Tìm Kiếm Trong BST (Search)

#### 6.7.1. Quy Trình Tìm Kiếm

Để tìm một giá trị trong BST:

1. **Bắt Đầu Từ Node Gốc:**
   - So sánh giá trị tìm kiếm với node hiện tại.

2. **Di Chuyển Xuống Cây Con:**
   - Nếu giá trị nhỏ hơn node hiện tại, di chuyển sang cây con bên trái.
   - Nếu lớn hơn, di chuyển sang cây con bên phải.

3. **Kết Thúc Khi Tìm Thấy Hoặc Đến Node Rỗng:**
   - Nếu tìm thấy node chứa giá trị cần tìm, trả về node đó.
   - Nếu đến node rỗng (`null`) mà chưa tìm thấy, giá trị không tồn tại trong cây.

#### 6.7.2. Minh Họa Tìm Kiếm

Giả sử chúng ta muốn tìm giá trị **7** trong BST:
```
       10
      /  \
     5    15
    / \     \
   3   7     20
         \
          8
```

- So sánh 7 với 10 → 7 < 10 → sang trái 10
- So sánh 7 với 5 → 7 > 5 → sang phải 5
- So sánh 7 với 7 → tìm thấy

### 6.8. Xóa Node trong BST (Delete)
- Học Fe là hiểu lười dạy
### 6.9. Duyệt Cây trong BST (Traverse)

#### 6.9.1. Các Loại Duyệt Cây Chính

- **Pre-order Traversal:**
  - Truy cập node cha trước, sau đó duyệt cây con bên trái và bên phải.
  
- **In-order Traversal:**
  - Duyệt cây con bên trái trước, sau đó truy cập node cha, và cuối cùng duyệt cây con bên phải.
  
- **Post-order Traversal:**
  - Duyệt cây con bên trái và bên phải trước, sau đó truy cập node cha.

#### 6.9.2. Minh Họa Các Loại Duyệt

Giả sử chúng ta có BST:
```
       10
      /  \
     5    15
    / \     \
   3   7     20
```

- **Pre-order Traversal:**
  ```
  10, 5, 3, 7, 15, 20
  ```

- **In-order Traversal:**
  ```
  3, 5, 7, 10, 15, 20
  ```

- **Post-order Traversal:**
  ```
  3, 7, 5, 20, 15, 10
  ```

### 6.9. Duyệt Cây trong BST (Traverse)

#### 6.9.1. Các Loại Duyệt Cây Chính

- **Pre-order Traversal:**
  - Truy cập node cha trước, sau đó duyệt cây con bên trái và bên phải.
  
- **In-order Traversal:**
  - Duyệt cây con bên trái trước, sau đó truy cập node cha, và cuối cùng duyệt cây con bên phải.
  
- **Post-order Traversal:**
  - Duyệt cây con bên trái và bên phải trước, sau đó truy cập node cha.

#### 6.9.2. Minh Họa Các Loại Duyệt

Giả sử chúng ta có BST:
```
       10
      /  \
     5    15
    / \     \
   3   7     20
```

- **Pre-order Traversal:**
  ```
  10, 5, 3, 7, 15, 20
  ```

- **In-order Traversal:**
  ```
  3, 5, 7, 10, 15, 20
  ```

- **Post-order Traversal:**
  ```
  3, 7, 5, 20, 15, 10
  ```

### 6.10. Duyệt Cây Theo Chiều Rộng (Breadth-First Traversal - BFS)

#### 6.10.1. Giới Thiệu

**Duyệt theo chiều rộng (Breadth-First Traversal - BFS)** là một phương pháp duyệt cây mà bắt đầu từ node gốc và duyệt qua tất cả các node ở cùng một mức độ trước khi chuyển sang mức độ tiếp theo. Phương pháp này thường được thực hiện bằng cách sử dụng một hàng đợi (queue).

#### 6.10.2. Cách Thức Hoạt Động

1. **Khởi Tạo Queue:**
   - Đặt node gốc vào queue.

2. **Lặp Lại Đến Khi Queue Trống:**
   - Lấy node đầu tiên từ queue và xử lý nó (ví dụ: in dữ liệu).
   - Thêm tất cả các node con (bên trái và bên phải) của node đó vào cuối queue.

#### 6.10.3. Minh Họa BFS

Sử dụng BST mẫu:
```
       10
      /  \
     5    15
    / \     \
   3   7     20
```

**Duyệt BFS:**
```
Queue ban đầu: [10]
Xử lý 10, thêm 5 và 15 vào queue.
Queue: [5, 15]

Xử lý 5, thêm 3 và 7 vào queue.
Queue: [15, 3, 7]

Xử lý 15, thêm 20 vào queue.
Queue: [3, 7, 20]

Xử lý 3, không có con.
Queue: [7, 20]

Xử lý 7, không có con.
Queue: [20]

Xử lý 20, không có con.
Queue: []

Kết quả BFS: 10, 5, 15, 3, 7, 20
```

### 6.11. Duyệt Cây Theo Chiều Sâu (Depth-First Traversal - DFS)

#### 6.11.1. Giới Thiệu

**Duyệt theo chiều sâu (Depth-First Traversal - DFS)** là một phương pháp duyệt cây mà bắt đầu từ node gốc và đi sâu vào một nhánh trước khi backtrack và duyệt nhánh tiếp theo. DFS có ba loại chính:

- **Pre-order Traversal**
- **In-order Traversal**
- **Post-order Traversal**

#### 6.11.2. So Sánh BFS và DFS

| Tiêu Chí         | BFS                            | DFS                                   |
|------------------|--------------------------------|---------------------------------------|
| **Phương Pháp**  | Duyệt theo mức độ, từ trên xuống | Duyệt sâu vào từng nhánh trước khi quay lại |
| **Cấu Trúc Dữ Liệu** | Hàng đợi (Queue)                  | Ngăn xếp (Stack) hoặc đệ quy (Recursion) |
| **Ứng Dụng**     | Tìm đường đi ngắn nhất, duyệt theo mức độ | Tìm kiếm sâu, kiểm tra cấu trúc cây |
| **Độ Phức Tạp**  | O(n)                            | O(n)                                   |

#### 6.11.3. Minh Họa DFS (Pre-order, In-order, Post-order)

Sử dụng BST mẫu:
```
       10
      /  \
     5    15
    / \     \
   3   7     20
```

- **Pre-order Traversal:**
  ```
  10, 5, 3, 7, 15, 20
  ```

- **In-order Traversal:**
  ```
  3, 5, 7, 10, 15, 20
  ```

- **Post-order Traversal:**
  ```
  3, 7, 5, 20, 15, 10
  ```

### 6.12. Độ Phức Tạp của Các Phép Toán trong BST

| Phép Toán | Độ Phức Tạp Trung Bình | Độ Phức Tạp Tốt Nhất | Độ Phức Tạp Xấu Nhất |
|-----------|------------------------|-----------------------|-----------------------|
| Insert    | O(log n)               | O(log n)              | O(n)                  |
| Search    | O(log n)               | O(1)                  | O(n)                  |
| Delete    | O(log n)               | O(log n)              | O(n)                  |
| Traverse  | O(n)                    | O(n)                  | O(n)                  |
| BFS       | O(n)                    | O(n)                  | O(n)                  |
| DFS       | O(n)                    | O(n)                  | O(n)                  |

- **Lưu Ý:** Độ phức tạp phụ thuộc vào độ cao của cây. Trong cây cân bằng, độ cao là O(log n), nhưng nếu cây trở thành một danh sách liên kết (cây mất cân bằng), độ cao có thể lên đến O(n).

---

## Chương 7: Cây AVL

### 7.1. Giới Thiệu

**Cây AVL** là một loại cây tìm kiếm nhị phân tự cân bằng, đảm bảo rằng độ cao của hai cây con của bất kỳ node nào không khác nhau hơn một đơn vị. Điều này giúp tối ưu hóa các phép toán tìm kiếm, chèn và xóa bằng cách giữ cho cây luôn cân bằng, từ đó duy trì độ phức tạp của các phép toán ở mức tối thiểu là O(log n).

### 7.2. Các Phép Toán Cơ Bản

- **Insert:** Thêm một node và cân bằng cây nếu cần.
- **Rotate:** Các phép quay đơn và đôi để cân bằng cây.

### 7.3. Cách Thức Hoạt Động

1. **Insert:** Sau khi chèn một node mới vào cây AVL, kiểm tra lại cân bằng của cây từ node vừa thay đổi trở lên.
2. **Rotate:** Nếu cây bị mất cân bằng, sử dụng các phép quay đơn hoặc đôi để khôi phục cân bằng.

### 7.4. Minh Họa

Trước khi cân bằng:
```
       30
      /
    20
    /
  10
```

Sau khi cân bằng bằng phép quay phải:
```
     20
    /  \
  10    30
```

### 7.5. Cân Bằng Cây AVL bằng Các Phép Quay

Khi chèn một node vào cây AVL, cây có thể mất cân bằng. Để khôi phục cân bằng, chúng ta sử dụng các phép quay đơn và đôi dựa trên các trường hợp mất cân bằng. Các trường hợp mất cân bằng chính bao gồm:

1. **Left-Left (LL) Case**
2. **Left-Right (LR) Case**
3. **Right-Right (RR) Case**
4. **Right-Left (RL) Case**

#### 7.5.1. Left-Left (LL) Case

**Khi xảy ra:**
- Node mới được chèn vào cây con bên trái của cây con bên trái của node mất cân bằng.

**Phép quay cần thiết:**
- **Quay phải đơn**

**Minh Họa:**

Trước khi quay:
```
       30
      /
    20
    /
  10
    \
     5
```

Sau khi quay phải:
```
     20
    /  \
  10    30
    \
     5
```

#### 7.5.2. Left-Right (LR) Case

**Khi xảy ra:**
- Node mới được chèn vào cây con bên phải của cây con bên trái của node mất cân bằng.

**Phép quay cần thiết:**
- **Quay trái đơn** trên cây con bên trái.
- **Quay phải đơn** trên node mất cân bằng.

**Minh Họa:**

Trước khi quay:
```
       30
      /
    20
    /
  10
    \
     15
```

Sau khi quay trái đơn trên node 10:
```
       30
      /
    20
    /
  15
  /
10
```

Sau khi quay phải đơn trên node 20:
```
     15
    /  \
  10    20
          \
           30
```

#### 7.5.3. Right-Right (RR) Case

**Khi xảy ra:**
- Node mới được chèn vào cây con bên phải của cây con bên phải của node mất cân bằng.

**Phép quay cần thiết:**
- **Quay trái đơn**

**Minh Họa:**

Trước khi quay:
```
 10
    \
     20
        \
         30
            \
             40
```

Sau khi quay trái đơn:
```
     20
    /  \
  10    30
           \
            40
```

#### 7.5.4. Right-Left (RL) Case

**Khi xảy ra:**
- Node mới được chèn vào cây con bên trái của cây con bên phải của node mất cân bằng.

**Phép quay cần thiết:**
- **Quay phải đơn** trên cây con bên phải.
- **Quay trái đơn** trên node mất cân bằng.

**Minh Họa:**

Trước khi quay:
```
    10
      \
       30
       /
     20
       \
        25
```

Sau khi quay phải đơn trên node 30:
```
    10
      \
       25
      /  \
    20    30
```

Sau khi quay trái đơn trên node 10:
```
     25
    /  \
  10    30
    \
     20
```

### 7.6. Quy Trình Cân Bằng Sau Khi Chèn Node

Khi chèn một node vào cây AVL, quy trình cân bằng như sau:

1. **Chèn Node như trong BST thông thường.**
2. **Duyệt ngược từ node vừa chèn lên đến node gốc, cập nhật cân bằng (balance factor) cho từng node.**
3. **Xác định xem node có bị mất cân bằng không (balance factor > 1 hoặc < -1).**
4. **Xác định loại mất cân bằng (LL, LR, RR, RL).**
5. **Thực hiện các phép quay phù hợp để khôi phục cân bằng.**

### 7.7. Ví Dụ Cụ Thể về Cân Bằng AVL Sau Khi Chèn

**Bài toán:**
Chèn các giá trị sau vào một cây AVL theo thứ tự: 10, 20, 30, 40, 50, 25.

**Quy trình:**

1. **Chèn 10:**
   ```
   10
   ```
   - Cân bằng: Được cân bằng.

2. **Chèn 20:**
   ```
     10
       \
        20
   ```
   - Cân bằng: Được cân bằng.

3. **Chèn 30:**
   ```
     10
       \
        20
          \
           30
   ```
   - Cân bằng: Mất cân bằng RR tại node 10.
   - Thực hiện quay trái đơn tại node 10:
```
       20
      /  \
    10    30

```

4. **Chèn 40:**
   ```
       20
      /  \
    10    30
            \
             40
   ```
   - Cân bằng: Được cân bằng.

5. **Chèn 50:**
   ```
       20
      /  \
    10    30
            \
             40
               \
                50
   ```
   - Cân bằng: Mất cân bằng RR tại node 30.
   - **Thực hiện quay trái đơn** tại node 30:
 ```
       20
      /  \
    10    40
          /  \
        30    50
 ```

6. **Chèn 25:**
   ```
       20
      /  \
    10    40
          /  \
        30    50
        /
      25
   ```
   - Cân bằng: Mất cân bằng RL tại node 40.
   - **Thực hiện quay phải đơn** tại node 30:
```
       20
      /  \
    10    40
          /  \
        25    50
          \
           30
```
   - **Thực hiện quay trái đơn** tại node 40:
 ```
       20
      /  \
    10    25
          /  \
        20    40
              \
               50
 ```


## Chương 8: Duyệt Cây - Pre-order, In-order, Post-order

### 8.1. Giới Thiệu

**Duyệt cây** là quá trình truy cập tất cả các node trong cây theo một trình tự nhất định. Có ba loại duyệt cây chính:

- **Pre-order:** Truy cập node cha trước, sau đó duyệt cây con bên trái và bên phải.
- **In-order:** Duyệt cây con bên trái trước, sau đó truy cập node cha, và cuối cùng duyệt cây con bên phải.
- **Post-order:** Duyệt cây con bên trái và bên phải trước, sau đó truy cập node cha.

### 8.2. Pre-order Traversal

#### Cách Thức Hoạt Động

1. Truy cập node cha.
2. Duyệt cây con bên trái.
3. Duyệt cây con bên phải.

#### Minh Họa

```
Pre-order: 10, 5, 3, 7, 15, 20
```

### 8.3. In-order Traversal

#### Cách Thức Hoạt Động

1. Duyệt cây con bên trái.
2. Truy cập node cha.
3. Duyệt cây con bên phải.

#### Minh Họa

```
In-order: 3, 5, 7, 10, 15, 20
```

### 8.4. Post-order Traversal

#### Cách Thức Hoạt Động

1. Duyệt cây con bên trái.
2. Duyệt cây con bên phải.
3. Truy cập node cha.

#### Minh Họa

```
Post-order: 3, 7, 5, 20, 15, 10
```

---

## Chương 9: Thuật Toán Dijkstra

### 9.1. Giới Thiệu

**Thuật toán Dijkstra** là một thuật toán tìm đường đi ngắn nhất từ một nút nguồn đến tất cả các nút khác trong một đồ thị có trọng số không âm.

### 9.2. Cách Thức Hoạt Động

1. **Khởi Tạo:** Đặt khoảng cách từ nguồn đến tất cả các nút khác là vô cùng, ngoại trừ nguồn (đặt là 0).
2. **Chọn Nút:** Chọn nút chưa được xử lý có khoảng cách nhỏ nhất.
3. **Cập Nhật Khoảng Cách:** Cập nhật khoảng cách đến các nút kề với nút đã chọn.
4. **Lặp Lại:** Lặp lại các bước cho đến khi tất cả các nút được xử lý.

### 9.3. Độ Phức Tạp

- **Với mảng đơn:** O(V^2), trong đó V là số lượng nút.
- **Với heap (priority queue):** O(E + V log V), trong đó E là số lượng cạnh.


---

## Chương 10: Các Thuật Toán Sắp Xếp

### 10.1. Giới Thiệu

**Thuật toán sắp xếp** là các thuật toán sắp xếp các phần tử trong một tập hợp theo một thứ tự nhất định (tăng dần hoặc giảm dần). Dưới đây là một số thuật toán sắp xếp phổ biến cùng với cách hoạt động và độ phức tạp của chúng.

### 10.2. Bubble Sort

#### Cách Thức Hoạt Động

- So sánh từng cặp phần tử kề nhau và hoán đổi nếu chúng không theo thứ tự.
- Lặp lại quá trình cho đến khi không còn hoán đổi nào.

#### Độ Phức Tạp

- **Trường hợp tốt nhất:** O(n) (khi mảng đã được sắp xếp).
- **Trường hợp trung bình và xấu nhất:** O(n^2).

#### Minh Họa

```
[5, 1, 4, 2, 8]
Bước 1: [1, 4, 2, 5, 8]
Bước 2: [1, 2, 4, 5, 8]
```

### 10.3. Selection Sort

#### Cách Thức Hoạt Động

- Tìm phần tử nhỏ nhất trong mảng chưa được sắp xếp.
- Hoán đổi phần tử nhỏ nhất đó với phần tử đầu tiên của mảng chưa sắp xếp.
- Lặp lại quá trình cho đến khi toàn bộ mảng được sắp xếp.

#### Độ Phức Tạp

- **Tất cả các trường hợp:** O(n^2).

#### Minh Họa

```
[64, 25, 12, 22, 11]
Bước 1: [11, 25, 12, 22, 64]
Bước 2: [11, 12, 25, 22, 64]
Bước 3: [11, 12, 22, 25, 64]
```

### 10.4. Insertion Sort

#### Cách Thức Hoạt Động

- Chia mảng thành hai phần: đã sắp xếp và chưa sắp xếp.
- Lấy phần tử từ phần chưa sắp xếp và chèn vào vị trí thích hợp trong phần đã sắp xếp.
- Lặp lại quá trình cho đến khi toàn bộ mảng được sắp xếp.

#### Độ Phức Tạp

- **Trường hợp tốt nhất:** O(n) (khi mảng đã được sắp xếp).
- **Trường hợp trung bình và xấu nhất:** O(n^2).

#### Minh Họa

```
[12, 11, 13, 5, 6]
Bước 1: [11, 12, 13, 5, 6]
Bước 2: [11, 12, 13, 5, 6]
Bước 3: [5, 11, 12, 13, 6]
Bước 4: [5, 6, 11, 12, 13]
```

### 10.5. Merge Sort

#### Cách Thức Hoạt Động

- Chia mảng thành hai nửa.
- Đệ quy sắp xếp từng nửa.
- Gộp hai nửa đã sắp xếp lại thành một mảng hoàn chỉnh.

#### Độ Phức Tạp

- **Tất cả các trường hợp:** O(n log n).

#### Minh Họa

```
[38, 27, 43, 3, 9, 82, 10]
Chia: [38, 27, 43, 3] và [9, 82, 10]
Sắp xếp: [27, 38, 43, 3] và [9, 10, 82]
Gộp: [3, 27, 38, 43, 9, 10, 82]
```

### 10.6. Quick Sort

#### Cách Thức Hoạt Động

- Chọn một phần tử làm pivot.
- Chia mảng thành hai phần: nhỏ hơn pivot và lớn hơn pivot.
- Đệ quy sắp xếp từng phần.

#### Độ Phức Tạp

- **Trường hợp tốt nhất và trung bình:** O(n log n).
- **Trường hợp xấu nhất:** O(n^2).

#### Minh Họa

```
[10, 7, 8, 9, 1, 5]
Chọn pivot = 5
Chia: [1] và [10, 7, 8, 9]
Sắp xếp lại: [1, 5, 7, 8, 9, 10]
```

### 10.7. Heap Sort

#### Cách Thức Hoạt Động

- Xây dựng một heap (cây heap) từ mảng.
- Loại bỏ phần tử lớn nhất (hoặc nhỏ nhất) từ heap và đặt vào cuối mảng.
- Cân bằng lại heap.
- Lặp lại quá trình cho đến khi heap rỗng.

#### Độ Phức Tạp

- **Tất cả các trường hợp:** O(n log n).

#### Minh Họa

```
Mảng ban đầu: [12, 11, 13, 5, 6, 7]
Xây dựng heap: [13, 12, 7, 5, 6, 11]
Loại bỏ phần tử lớn nhất: [11, 12, 7, 5, 6] và [13]
Cân bằng lại heap: [12, 6, 7, 5, 11]
```

### 10.8. Độ Phức Tạp và So Sánh Các Thuật Toán

| Thuật Toán    | Trường Hợp Tốt Nhất | Trường Hợp Trung Bình | Trường Hợp Xấu Nhất | Không Gian |
|---------------|---------------------|-----------------------|---------------------|------------|
| Bubble Sort   | O(n)                | O(n²)                 | O(n²)               | O(1)       |
| Selection Sort| O(n²)               | O(n²)                 | O(n²)               | O(1)       |
| Insertion Sort| O(n)                | O(n²)                 | O(n²)               | O(1)       |
| Merge Sort    | O(n log n)          | O(n log n)            | O(n log n)          | O(n)       |
| Quick Sort    | O(n log n)          | O(n log n)            | O(n²)               | O(log n)    |
| Heap Sort     | O(n log n)          | O(n log n)            | O(n log n)          | O(1)       |

### 10.9. Bài Tập Thực Hành

1. **So Sánh Độ Phức Tạp của Bubble Sort và Quick Sort:**
   
   **Giải Thích:**
   - Bubble Sort có độ phức tạp O(n²) trong mọi trường hợp, làm cho nó kém hiệu quả với mảng lớn.
   - Quick Sort có độ phức tạp O(n log n) trong trường hợp trung bình và tốt nhất, làm cho nó nhanh hơn đáng kể đối với mảng lớn.

2. **Giải Thích Tại Sao Merge Sort Có Không Gian O(n):**
   
   **Giải Thích:**
   - Merge Sort cần một mảng phụ để gộp hai mảng con đã sắp xếp, dẫn đến không gian bổ sung O(n).

3. **Trình Bày Các Trường Hợp Xấu Nhất của Quick Sort và Cách Giảm Thiểu:**
   
   **Giải Thích:**
   - Trường hợp xấu nhất xảy ra khi pivot được chọn không tốt, dẫn đến phân chia không cân bằng (ví dụ: mảng đã được sắp xếp).
   - Giảm thiểu bằng cách chọn pivot ngẫu nhiên hoặc sử dụng phương pháp "median-of-three".

---
