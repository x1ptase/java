
1. Trong hàm `addLast` của `dataList`, điều kiện để thêm một phòng vào cuối danh sách là `status == 0`. Đây là code:

```java
void addLast(String code, int status, int size, int price) {
    if (status == 0) {  // CHỈ thêm phòng khi status = 0
        Node q = new Node(new Room(code, status, size, price));
        if (isEmpty()) {
            head = tail = q;
            return;
        }
        tail.next = q;
        tail = q;
    }
}
```

Giải thích chi tiết:
1. Điều kiện `status == 0`:
   - Chỉ thêm phòng vào danh sách nếu status = 0 (phòng trống)
   - Nếu status ≠ 0, hàm sẽ không thực hiện gì cả

2. Nếu thỏa mãn điều kiện:
   - Tạo node mới với thông tin phòng
   - Nếu danh sách rỗng: node mới sẽ là cả head và tail
   - Nếu không rỗng: thêm node mới vào sau tail và cập nhật tail

3. Các tham số:
   - code: mã phòng
   - status: trạng thái phòng (0 = trống)
   - size: kích thước phòng
   - price: giá phòng


2. Chúng ta cần tìm phòng phù hợp với yêu cầu. Một phòng phù hợp cần:
   - Đang trống (status = 0)
   - Có kích thước đủ lớn (size >= yêu cầu)
   - Có giá phù hợp (price <= yêu cầu)

```java
void rent(Room t) throws Exception {
    // Bước 1: Khởi tạo biến để duyệt danh sách và lưu kết quả
    Node current = dList.head;  // Con trỏ để duyệt danh sách
    Room tmp = null;  // Lưu phòng tốt nhất tìm được
    
    // Bước 2: Tìm phòng đầu tiên phù hợp
    while(current != null) {
        // Kiểm tra từng điều kiện
        if(current.info.getStatus() == 0) {  // Phòng còn trống
            if(current.info.getSize() >= t.getSize()) {  // Đủ kích thước
                if(current.info.getPrice() <= t.getPrice()) {  // Giá phù hợp
                    tmp = current.info;  // Lưu phòng phù hợp đầu tiên
                    break;  // Thoát vòng lặp
                }
            }
        }
        current = current.next;  // Chuyển sang phòng tiếp theo
    }
    
    // Bước 3: Tìm phòng có giá rẻ nhất trong số các phòng phù hợp
    while(current != null) {
        if(current.info.getStatus() == 0) {
            if(current.info.getSize() >= t.getSize()) {
                if(current.info.getPrice() <= t.getPrice()) {
                    // So sánh với phòng đã tìm được
                    if(current.info.getPrice() < tmp.getPrice()) {
                        tmp = current.info;  // Cập nhật nếu tìm được phòng rẻ hơn
                    }
                }
            }
        }
        current = current.next;
    }
    
    // Bước 4: Đặt phòng nếu tìm được
    if(tmp != null) {
        tmp.setStatus(1);  // Đánh dấu phòng đã được thuê
    }
}
```

Để sử dụng hàm này trong f2, f3, và f4:

```java
// f2: Xử lý một yêu cầu
Room request = RQueue.deQueue();  // Lấy yêu cầu đầu tiên
if (request != null) {
    rent(request);  // Xử lý yêu cầu đó
}

// f3: Xử lý tất cả yêu cầu
Room request = RQueue.deQueue();
while (request != null) {  // Lặp cho đến khi hết yêu cầu
    rent(request);  // Xử lý yêu cầu hiện tại
    request = RQueue.deQueue();  // Lấy yêu cầu tiếp theo
}

// f4: Xử lý tất cả yêu cầu và đếm phòng trống
int count = 0;  // Biến đếm phòng trống

// Xử lý các yêu cầu
Room request = RQueue.deQueue();
while (request != null) {
    rent(request);
    request = RQueue.deQueue();
}

// Đếm phòng trống
Node current = dList.head;
while(current != null) {
    if(current.info.getStatus() == 0) {  // Nếu phòng còn trống
        ++count;  // Tăng biến đếm
    }
    current = current.next;
}
```

Giải thích logic:
1. Hàm `rent`:
   - Duyệt danh sách để tìm phòng phù hợp đầu tiên
   - Tiếp tục duyệt để tìm phòng rẻ hơn (nếu có)
   - Đánh dấu phòng đã chọn là đã thuê

2. Xử lý yêu cầu:
   - f2: Chỉ xử lý một yêu cầu
   - f3: Xử lý tất cả yêu cầu
   - f4: Xử lý tất cả yêu cầu + đếm phòng trống

3. Đếm phòng trống:
   - Duyệt lại danh sách
   - Đếm các phòng có status = 0
