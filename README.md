# WWW_Java_Lab05_MauThiKimTho
TRANG WEB TUYỂN DỤNG VIỆC LÀM
Đây là một ứng dụng web dùng để quản lý công việc và ứng viên, được xây dựng bằng Spring Boot. Hệ thống cho phép các công ty đăng tin tuyển dụng, và ứng viên có thể ứng tuyển dựa trên kỹ năng và sở thích của mình. Ứng dụng cũng cung cấp tính năng gợi ý công việc phù hợp với ứng viên và cho phép các công ty mời ứng viên qua email.
CÁC TÍNH NĂNG:
Chức năng Quản lý Ứng viên (Candidate) 
•	Thêm ứng viên mới: Hiển thị form để nhập thông tin ứng viên và lưu vào cơ sở dữ liệu. 
•	Cập nhật thông tin ứng viên: Cung cấp form để chỉnh sửa thông tin ứng viên đã có. 
•	Xóa ứng viên: Xóa thông tin ứng viên khỏi cơ sở dữ liệu. 
•	Danh sách ứng viên: Hiển thị danh sách tất cả ứng viên hoặc phân trang danh sách ứng viên. 
•	Đăng nhập và hồ sơ ứng viên: Xử lý đăng nhập cho ứng viên và hiển thị thông tin cá nhân. 
•	Thống kê ứng viên theo thời gian: Cung cấp báo cáo thống kê số lượng ứng viên theo tháng/quý/năm. 
•	Thống kê ứng viên theo kỹ năng: Phân tích số lượng ứng viên có các kỹ năng cụ thể. 
•	Thống kê ứng viên theo vị trí địa lý: Hiển thị số lượng ứng viên theo từng khu vực địa lý.

Chức năng Quản lý Công ty (Company) 
•	Đăng ký công ty: Cho phép công ty đăng ký tài khoản mới. 
•	Đăng nhập công ty: Xử lý đăng nhập cho công ty và lưu thông tin vào phiên. 
•	Cập nhật thông tin công ty: Cung cấp form để chỉnh sửa thông tin công ty đã có. 
•	Hồ sơ công ty: Hiển thị thông tin và danh sách việc làm của công ty. 
•	Thống kê số lượng công ty theo ngành nghề: Cung cấp báo cáo về số lượng công ty hoạt động trong các lĩnh vực khác nhau. 
•	Thống kê hoạt động tuyển dụng của công ty: Phân tích số lượng việc làm được đăng tuyển và số lượng ứng viên nộp hồ sơ cho từng công ty. 

Chức năng Quản lý Việc làm (Job) Đăng tin tuyển dụng: 
•	Hiển thị form để công ty đăng tin tuyển dụng mới. 
•	Danh sách ứng viên cho việc làm: Hiển thị danh sách ứng viên phù hợp với một công việc cụ thể.(Gợi ý công việc cho ứng viên)
•	Thống kê số lượng việc làm theo loại hình: Cung cấp báo cáo về số lượng việc làm theo loại hình (toàn thời gian, bán thời gian, thực tập, v.v.). 
•	Thống kê ứng viên theo việc làm: Phân tích số lượng ứng viên nộp hồ sơ cho từng công việc cụ thể. 

Chức năng Quản lý Kỹ năng (Skill) 
•	Thêm kỹ năng cho ứng viên: Cho phép ứng viên thêm kỹ năng mới vào hồ sơ của họ. 
•	Xóa kỹ năng: Cho phép ứng viên xóa kỹ năng khỏi hồ sơ của họ. 
•	Thống kê kỹ năng phổ biến: Cung cấp báo cáo về các kỹ năng phổ biến nhất trong từng ngành nghề. 
•	Dự đoán kỹ năng cần thiết: Phân tích xu hướng thị trường để dự đoán các kỹ năng mà ứng viên nên có trong tương lai. 

Chức năng Quản lý Địa chỉ (Address) 
•	Tạo và cập nhật địa chỉ: Địa chỉ được tạo và cập nhật khi thêm hoặc sửa thông tin ứng viên và công ty. 

Chức năng Dự đoán (Predictive Analytics) 
•	Dự đoán nhu cầu tuyển dụng: Sử dụng thuật toán học máy để dự đoán số lượng việc làm mà một công ty có thể cần trong tương lai. 
•	Cung cấp báo cáo tùy chỉnh: Cho phép người dùng tạo báo cáo tùy chỉnh dựa trên các tiêu chí như thời gian, loại hình việc làm, khu vực địa lý, v.v.

CÔNG NGHỆ SỬ DỤNG:
•	Backend:
o	Spring Boot: Framework phát triển dịch vụ backend dựa trên Java.
o	Spring Data JPA: Dùng để tương tác với cơ sở dữ liệu và thực hiện các thao tác CRUD.
o	Thymeleaf: Công cụ tạo template động cho các trang HTML.
o	JavaMail API: Dùng để gửi thông báo qua email.
•	Frontend:
o	HTML/CSS: Công nghệ frontend cơ bản để tạo các form và layout.
o	Bootstrap: Framework CSS giúp thiết kế giao diện responsive.
•	Cơ sở dữ liệu:
o	MariaDB: Cơ sở dữ liệu quan hệ để lưu trữ dữ liệu công việc và ứng viên.

