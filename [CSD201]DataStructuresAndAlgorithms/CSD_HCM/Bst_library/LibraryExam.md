# ĐỀ THI THỰC HÀNH CẤU TRÚC DỮ LIỆU VÀ GIẢI THUẬT

## Tổng quan
Bài thi yêu cầu sinh viên hoàn thiện một hệ thống quản lý thư viện sử dụng cấu trúc dữ liệu BST (Binary Search Tree) và Queue. Hệ thống sẽ quản lý danh mục sách và các yêu cầu mượn sách.

## Thời gian: 90 phút
## Điểm: 10 điểm

## Mô tả hệ thống
Hệ thống thư viện gồm 2 phần chính:
1. **BST (Binary Search Tree)**: Lưu trữ thông tin về sách trong thư viện, sắp xếp theo mã sách (code)
2. **Queue**: Quản lý danh sách các yêu cầu mượn sách theo thứ tự FIFO (First In First Out)

### Thông tin sách (Book)
- Mã sách (code): Mã định danh duy nhất cho mỗi cuốn sách
- Tên sách (title): Tên của cuốn sách
- Số lượng (quantity): Số lượng sách hiện có
- Danh mục (category): Phân loại sách (1: Giáo trình, 2: Tham khảo, 3: Tiểu thuyết, 4: Khác)

### Yêu cầu mượn sách
- Tên sách (title): Tên sách cần mượn
- Số lượng (quantity): Số lượng sách cần mượn

## Cấu trúc chương trình
Chương trình đã được khởi tạo với các file sau:
- **Main.java**: File chương trình chính, không được sửa đổi
- **Lib.java**: File hỗ trợ đọc/ghi file, không được sửa đổi
- **Book.java**: Class định nghĩa thông tin sách
- **Node.java**: Class Node cho BST
- **Node2.java**: Class Node cho Queue
- **Library.java**: Class chính chứa các thao tác trên BST và Queue
- **data.txt**: File dữ liệu chứa thông tin sách và yêu cầu mượn sách

## Nhiệm vụ của sinh viên
Sinh viên cần hoàn thiện các phương thức trong file **Library.java** để thực hiện các chức năng sau:

### 1. Chức năng f1(): Hiển thị dữ liệu (2.5 điểm)
- Đọc dữ liệu từ file data.txt
- Hiển thị danh sách sách trong BST và danh sách yêu cầu mượn sách trong Queue
- Lưu kết quả vào file f1.txt

### 2. Chức năng f2(): Xử lý một yêu cầu mượn sách (2.5 điểm)
- Đọc dữ liệu từ file data.txt
- Lấy và xử lý một yêu cầu mượn sách từ Queue
- Tìm sách phù hợp trong BST và giảm số lượng sách nếu tìm thấy
- Nếu số lượng sách sau khi mượn giảm xuống 0, loại bỏ sách khỏi BST
- Lưu trạng thái của BST và Queue trước và sau khi xử lý vào file f2.txt

### 3. Chức năng f3(): Xử lý tất cả yêu cầu mượn sách (2.5 điểm)
- Đọc dữ liệu từ file data.txt
- Lấy và xử lý tất cả yêu cầu mượn sách từ Queue
- Tìm sách phù hợp trong BST và giảm số lượng sách nếu tìm thấy
- Nếu số lượng sách sau khi mượn giảm xuống 0, loại bỏ sách khỏi BST
- Lưu trạng thái của BST và Queue trước và sau khi xử lý vào file f3.txt

### 4. Chức năng f4(): Đếm tổng số sách còn lại (2.5 điểm)
- Đọc dữ liệu từ file data.txt
- Xử lý tất cả yêu cầu mượn sách từ Queue
- Đếm tổng số sách còn lại trong BST sau khi xử lý tất cả yêu cầu
- Lưu kết quả vào file f4.txt

## Quy định
1. **Thêm sách vào BST**:
   - Không thêm sách nếu số lượng (quantity) hoặc danh mục (category) <= 0
   - Không thêm sách nếu mã sách (code) đã tồn tại trong BST
   - Sách được sắp xếp trong BST theo mã sách (code)

2. **Thêm yêu cầu mượn sách vào Queue**:
   - Không thêm yêu cầu nếu số lượng (quantity) <= 0
   - Các yêu cầu được xử lý theo thứ tự FIFO (First In First Out)

3. **Xử lý yêu cầu mượn sách**:
   - Tìm kiếm sách theo tên (title) chứ không phải theo mã sách (code)
   - Nếu có nhiều sách cùng tên, chọn sách có danh mục nhỏ nhất (category tốt nhất)
   - Chỉ giảm số lượng sách nếu tìm thấy sách phù hợp và số lượng đủ
   - Nếu số lượng sách giảm xuống 0 hoặc âm, xóa sách khỏi BST
   - Nếu không tìm thấy sách phù hợp hoặc số lượng không đủ, bỏ qua yêu cầu

4. **Tìm sách phù hợp**:
   - Sách phù hợp phải thỏa mãn các điều kiện:
     - Tên sách trùng với tên sách trong yêu cầu (không phân biệt hoa thường)
     - Số lượng sách đủ để đáp ứng yêu cầu (quantity trong sách >= quantity trong yêu cầu)
   - Nếu có nhiều sách thỏa mãn, chọn sách có danh mục nhỏ nhất (giáo trình > tham khảo > tiểu thuyết > khác)

5. **Đếm số sách**:
   - Đếm tổng số sách còn lại (tổng quantity) sau khi xử lý tất cả yêu cầu mượn sách
   - Nếu sách có quantity = 0, không được tính vào tổng số sách (đã bị xóa khỏi BST)

## Định dạng đầu vào (file data.txt)
```
B001 B002 B003 B004 B005 B006 B007            // Dòng 0: Mã sách
Lap Trinh Java CSHARP DOTNET ASPNET PYTHON    // Dòng 1: Tên sách
5 3 4 2 3 1 6                                // Dòng 2: Số lượng sách
1 1 1 2 2 3 4                                // Dòng 3: Danh mục sách
Java Python CSHARP Ruby PHP                   // Dòng 4: Tên sách cần mượn
2 1 3 2 1                                    // Dòng 5: Số lượng sách cần mượn
```

## Định dạng đầu ra
Kết quả sẽ được lưu vào các file f1.txt, f2.txt, f3.txt, f4.txt tương ứng với các chức năng.

## Lưu ý
- Sinh viên chỉ được sửa đổi file Library.java
- Không được thay đổi cấu trúc của các class
- Không được thay đổi chữ ký của các phương thức
- Các file Main.java và Lib.java không được sửa đổi
- Comment rõ ràng code của bạn để giải thích các quyết định thiết kế

## Tiêu chí đánh giá
- Chương trình chạy đúng và đủ chức năng
- Code sạch, rõ ràng, có comment đầy đủ
- Xử lý đúng các trường hợp đặc biệt và điều kiện
- Sử dụng đúng cấu trúc BST và Queue theo yêu cầu
- Tuân thủ các quy định đề ra

## Thực hiện
1. Mở file Library.java để hoàn thiện các phương thức theo yêu cầu
2. Chạy Main.java để kiểm tra kết quả
3. Nộp file Library.java sau khi hoàn thành 