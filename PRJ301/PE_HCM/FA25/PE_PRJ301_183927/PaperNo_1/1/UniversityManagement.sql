USE [master]
GO
-- 0. Xóa Database UniversityManagement nếu đã tồn tại trong DBMS --------------------
IF EXISTS (
    SELECT name 
    FROM sys.databases 
    WHERE name = N'UniversityManagement'
)
BEGIN
    -- Tùy chọn: đưa DB về SINGLE_USER để tránh lỗi đang có kết nối
    ALTER DATABASE [UniversityManagement] SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    
    -- Xóa database
    DROP DATABASE [UniversityManagement];
END
GO
/****** Object:  Database [UniversityManagement]   *****/
CREATE DATABASE [UniversityManagement] 
GO

USE [UniversityManagement]
GO

-- Dành cho người dùng (Users)
CREATE TABLE [dbo].[tblUsers](
	[userID] [varchar](25) NOT NULL,
	[fullName] [nvarchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[roleID] [varchar](5) NOT NULL default 'NV',
	[status] [bit] NOT NULL default 0,
 CONSTRAINT [PK_tblUsers] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

-- Dữ liệu mẫu cho bảng Users (Admin, Phó Hiệu trưởng, Trưởng khoa, Giảng viên)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [status]) VALUES (N'admin', N'Administrator', N'1', N'QL', 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [status]) VALUES (N'pht001', N'PGS.TS Nguyễn Văn Minh', N'1', N'QL', 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [status]) VALUES (N'tk001', N'TS. Trần Thị Lan Anh', N'hcm', N'QL', 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [status]) VALUES (N'tk002', N'PGS.TS Lê Văn Đức', N'hn', N'QL', 0)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [status]) VALUES (N'gv001', N'ThS. Phạm Thị Hồng', N'dn', N'NV', 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [status]) VALUES (N'gv002', N'TS. Hoàng Văn Nam', N'qn', N'NV', 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [status]) VALUES (N'gv003', N'ThS. Võ Thị Mai', N'ct', N'NV', 0)
GO

-- Lưu trữ thông tin của các Trường Đại học
CREATE TABLE [dbo].[tblUniversity](
	[id] [char](6) NOT NULL,
	[name] [nvarchar](150) NOT NULL,
	[shortName] [nvarchar](20) NOT NULL,
	[description] [nvarchar](500) NOT NULL,
	[foundedYear] [int] NOT NULL,
	[address] [nvarchar](200) NOT NULL,
	[city] [nvarchar](50) NOT NULL,
	[region] [nvarchar](50) NOT NULL,
	[type] [nvarchar](50) NOT NULL,
	[totalStudents] [int] NOT NULL,
	[totalFaculties] [int] NOT NULL,
	[isDraft] bit default 1,
 CONSTRAINT [PK_tblUniversity] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

-- Dữ liệu mẫu cho table University (Một số trường đại học nổi tiếng tại Việt Nam)
-- Lưu ý: Dữ liệu được sử dụng chỉ dùng để mô phỏng cho đề thi (Không phải dữ liệu thực tế)
INSERT [dbo].[tblUniversity] ([id], [name], [shortName], [description], [foundedYear], [address], [city], [region], [type], [totalStudents], [totalFaculties],[isDraft]) 
VALUES (N'UNI001', N'Đại học Quốc gia Hà Nội', N'VNU-HN', N'Đại học đa ngành quốc gia đầu tiên của Việt Nam, đào tạo chất lượng cao', 1906, N'144 Xuân Thủy, Cầu Giấy', N'Hà Nội', N'Miền Bắc', N'Đại học Quốc gia', 45000, 16, 0)

INSERT [dbo].[tblUniversity] ([id], [name], [shortName], [description], [foundedYear], [address], [city], [region], [type], [totalStudents], [totalFaculties],[isDraft]) 
VALUES (N'UNI002', N'Đại học Bách khoa Hà Nội', N'HUST', N'Trường đại học kỹ thuật hàng đầu Việt Nam, chuyên đào tạo kỹ sư chất lượng cao', 1956, N'1 Đại Cồ Việt, Hai Bà Trưng', N'Hà Nội', N'Miền Bắc', N'Đại học Công lập', 35000, 14, 0)

INSERT [dbo].[tblUniversity] ([id], [name], [shortName], [description], [foundedYear], [address], [city], [region], [type], [totalStudents], [totalFaculties],[isDraft]) 
VALUES (N'UNI003', N'Đại học Quốc gia TP.HCM', N'VNU-HCM', N'Đại học quốc gia lớn nhất miền Nam, trung tâm giáo dục đại học của khu vực', 1995, N'Linh Trung, Thủ Đức', N'TP. Hồ Chí Minh', N'Miền Nam', N'Đại học Quốc gia', 52000, 18,0)

INSERT [dbo].[tblUniversity] ([id], [name], [shortName], [description], [foundedYear], [address], [city], [region], [type], [totalStudents], [totalFaculties],[isDraft]) 
VALUES (N'UNI004', N'Đại học Bách khoa TP.HCM', N'HCMUT', N'Trường đại học kỹ thuật hàng đầu miền Nam, đào tạo nguồn nhân lực kỹ thuật chất lượng cao', 1957, N'268 Lý Thường Kiệt, Quận 10', N'TP. Hồ Chí Minh', N'Miền Nam', N'Đại học Công lập', 32000, 15,0)

INSERT [dbo].[tblUniversity] ([id], [name], [shortName], [description], [foundedYear], [address], [city], [region], [type], [totalStudents], [totalFaculties],[isDraft]) 
VALUES (N'UNI005', N'Đại học Kinh tế Quốc dân', N'NEU', N'Trường đại học kinh tế hàng đầu Việt Nam, chuyên đào tạo cán bộ kinh tế', 1956, N'207 Giải Phóng, Hai Bà Trưng', N'Hà Nội', N'Miền Bắc', N'Đại học Công lập', 28000, 12,0)

INSERT [dbo].[tblUniversity] ([id], [name], [shortName], [description], [foundedYear], [address], [city], [region], [type], [totalStudents], [totalFaculties],[isDraft]) 
VALUES (N'UNI006', N'Đại học Y Hà Nội', N'HMU', N'Trường y khoa lâu đời và uy tín nhất Việt Nam, đào tạo bác sĩ chất lượng cao', 1902, N'1 Tôn Thất Tùng, Đống Đa', N'Hà Nội', N'Miền Bắc', N'Đại học Công lập', 18000, 8,0)

INSERT [dbo].[tblUniversity] ([id], [name], [shortName], [description], [foundedYear], [address], [city], [region], [type], [totalStudents], [totalFaculties]) 
VALUES (N'UNI007', N'Đại học Huế', N'HU', N'Trường đại học vùng miền Trung, có lịch sử lâu đời và truyền thống học thuật', 1957, N'3 Lê Lợi, Thành phố Huế', N'Thừa Thiên Huế', N'Miền Trung', N'Đại học Công lập', 24000, 13)

INSERT [dbo].[tblUniversity] ([id], [name], [shortName], [description], [foundedYear], [address], [city], [region], [type], [totalStudents], [totalFaculties],[isDraft]) 
VALUES (N'UNI008', N'Đại học FPT', N'FPT-U', N'Trường đại học tư thục chuyên về CNTT và kinh doanh, mô hình giáo dục hiện đại', 2006, N'Khu Công nghệ cao Hòa Lạc', N'Hà Nội', N'Miền Bắc', N'Đại học Tư thục', 15000, 6,0)

INSERT [dbo].[tblUniversity] ([id], [name], [shortName], [description], [foundedYear], [address], [city], [region], [type], [totalStudents], [totalFaculties]) 
VALUES (N'UNI009', N'Đại học Đà Nẵng', N'UDN', N'Trường đại học khu vực miền Trung, trung tâm giáo dục của vùng duyên hải Nam Trung Bộ', 1994, N'41 Lê Duẩn, Hải Châu', N'Đà Nẵng', N'Miền Trung', N'Đại học Công lập', 26000, 11)

GO