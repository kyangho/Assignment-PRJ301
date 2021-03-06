
/****** Object:  Database [Threater_PRJ_Assignment]    Script Date: 11/6/2021 11:39:49 PM ******/
IF EXISTS 
        (SELECT 
             TABLE_NAME 
         FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'Threater_PRJ_Assignment')
DROP TABLE [Threater_PRJ_Assignment]
GO
CREATE DATABASE [Threater_PRJ_Assignment]
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Threater_PRJ_Assignment].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET ARITHABORT OFF 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET RECOVERY FULL 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET  MULTI_USER 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'Threater_PRJ_Assignment', N'ON'
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET QUERY_STORE = OFF
GO
USE [Threater_PRJ_Assignment]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 11/6/2021 11:39:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[username] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NOT NULL,
	[displayname] [nvarchar](50) NOT NULL,
	[email] [text] NOT NULL,
	[phone] [nvarchar](20) NOT NULL,
	[dob] [date] NOT NULL,
	[gender] [bit] NOT NULL,
 CONSTRAINT [PK_account_1] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cinema]    Script Date: 11/6/2021 11:39:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cinema](
	[cinema_id] [int] NOT NULL,
	[cinema_name] [nvarchar](50) NOT NULL,
	[cinema_address] [nvarchar](150) NOT NULL,
	[cinema_phone] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Cinema] PRIMARY KEY CLUSTERED 
(
	[cinema_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cinema_Movie_Showing]    Script Date: 11/6/2021 11:39:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cinema_Movie_Showing](
	[cinema_id] [int] NOT NULL,
	[movie_showing_id] [int] NOT NULL,
 CONSTRAINT [PK_Cinema_Movie_Showing] PRIMARY KEY CLUSTERED 
(
	[cinema_id] ASC,
	[movie_showing_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cinema_Performance_Number]    Script Date: 11/6/2021 11:39:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cinema_Performance_Number](
	[cinema_id] [int] NOT NULL,
	[performance_number] [int] NOT NULL,
 CONSTRAINT [PK_Cinema_Performance_Number] PRIMARY KEY CLUSTERED 
(
	[cinema_id] ASC,
	[performance_number] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Feature]    Script Date: 11/6/2021 11:39:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Feature](
	[fid] [int] NOT NULL,
	[url] [nvarchar](150) NOT NULL,
 CONSTRAINT [PK_Feature] PRIMARY KEY CLUSTERED 
(
	[fid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Group]    Script Date: 11/6/2021 11:39:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Group](
	[gid] [int] NOT NULL,
	[gname] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_group] PRIMARY KEY CLUSTERED 
(
	[gid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GroupAccount]    Script Date: 11/6/2021 11:39:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GroupAccount](
	[gid] [int] NOT NULL,
	[username] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_GroupAccount_1] PRIMARY KEY CLUSTERED 
(
	[gid] ASC,
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GroupFeature]    Script Date: 11/6/2021 11:39:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GroupFeature](
	[gid] [int] NOT NULL,
	[fid] [int] NOT NULL,
 CONSTRAINT [PK_GroupFeature] PRIMARY KEY CLUSTERED 
(
	[gid] ASC,
	[fid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Movie]    Script Date: 11/6/2021 11:39:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Movie](
	[movie_id] [int] IDENTITY(1,1) NOT NULL,
	[movie_name] [nvarchar](50) NOT NULL,
	[movie_director] [nvarchar](50) NOT NULL,
	[movie_cast] [nvarchar](200) NOT NULL,
	[movie_running_time] [date] NOT NULL,
	[movie_duration] [int] NOT NULL,
	[movie_rated] [nvarchar](10) NOT NULL,
	[movie_description] [nvarchar](1000) NOT NULL,
	[movie_url_trailer] [text] NOT NULL,
	[movie_url_image] [text] NOT NULL,
 CONSTRAINT [PK_Movie] PRIMARY KEY CLUSTERED 
(
	[movie_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Movie_Genre]    Script Date: 11/6/2021 11:39:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Movie_Genre](
	[movie_genre_id] [int] IDENTITY(1,1) NOT NULL,
	[movie_genre] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Movie_Genre] PRIMARY KEY CLUSTERED 
(
	[movie_genre_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Movie_Language]    Script Date: 11/6/2021 11:39:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Movie_Language](
	[movie_language_id] [int] IDENTITY(1,1) NOT NULL,
	[movie_language] [nchar](10) NOT NULL,
 CONSTRAINT [PK_Movie_Language_1] PRIMARY KEY CLUSTERED 
(
	[movie_language_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Movie_Movie_Genre]    Script Date: 11/6/2021 11:39:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Movie_Movie_Genre](
	[movie_id] [int] NOT NULL,
	[movie_genre_id] [int] NOT NULL,
 CONSTRAINT [PK_Movie_Movie_Genre] PRIMARY KEY CLUSTERED 
(
	[movie_id] ASC,
	[movie_genre_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Movie_Movie_Language]    Script Date: 11/6/2021 11:39:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Movie_Movie_Language](
	[movie_id] [int] NOT NULL,
	[movie_language_id] [int] NOT NULL,
 CONSTRAINT [PK_Movie_Movie_Language] PRIMARY KEY CLUSTERED 
(
	[movie_id] ASC,
	[movie_language_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Movie_Movie_Showing]    Script Date: 11/6/2021 11:39:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Movie_Movie_Showing](
	[movie_showing_id] [int] NOT NULL,
	[movie_id] [int] NOT NULL,
 CONSTRAINT [PK_Movie_Movie_Showing] PRIMARY KEY CLUSTERED 
(
	[movie_showing_id] ASC,
	[movie_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Movie_Rated]    Script Date: 11/6/2021 11:39:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Movie_Rated](
	[movie_rated] [nvarchar](10) NOT NULL,
	[movie_rated_description] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Movie_Rated] PRIMARY KEY CLUSTERED 
(
	[movie_rated] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Movie_Showing]    Script Date: 11/6/2021 11:39:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Movie_Showing](
	[movie_showing_id] [int] IDENTITY(5,1) NOT NULL,
	[showing_from_date] [date] NOT NULL,
	[showing_to_date] [date] NULL,
 CONSTRAINT [PK_Movie_Showing] PRIMARY KEY CLUSTERED 
(
	[movie_showing_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Performance_Number]    Script Date: 11/6/2021 11:39:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Performance_Number](
	[performance_number] [int] NOT NULL,
	[performance_from_time] [time](0) NOT NULL,
	[performance_to_time] [time](0) NOT NULL,
 CONSTRAINT [PK_Performance_Number] PRIMARY KEY CLUSTERED 
(
	[performance_number] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Price]    Script Date: 11/6/2021 11:39:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Price](
	[price_id] [int] NOT NULL,
	[price_type] [nvarchar](50) NOT NULL,
	[day] [int] NOT NULL,
	[price] [float] NOT NULL,
 CONSTRAINT [PK_Price_Listing] PRIMARY KEY CLUSTERED 
(
	[price_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Row_Seat]    Script Date: 11/6/2021 11:39:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Row_Seat](
	[cinema_id] [int] NOT NULL,
	[row_number] [int] NOT NULL,
	[seat_count] [int] NOT NULL,
 CONSTRAINT [PK_Row_Seat] PRIMARY KEY CLUSTERED 
(
	[cinema_id] ASC,
	[row_number] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Seat_Status]    Script Date: 11/6/2021 11:39:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Seat_Status](
	[seat_status_code] [int] NOT NULL,
	[seat_status_description] [nvarchar](15) NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ticket]    Script Date: 11/6/2021 11:39:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ticket](
	[ticket_id] [int] IDENTITY(1,1) NOT NULL,
	[cinema_id] [int] NOT NULL,
	[row_number] [int] NOT NULL,
	[seat_number] [int] NOT NULL,
	[movie_id] [int] NOT NULL,
	[ticket_purchase_date] [date] NOT NULL,
	[performance_number] [int] NOT NULL,
	[price_id] [int] NOT NULL,
	[transaction_id] [int] NOT NULL,
 CONSTRAINT [PK_Ticket] PRIMARY KEY CLUSTERED 
(
	[ticket_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Transaction]    Script Date: 11/6/2021 11:39:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Transaction](
	[transaction_id] [int] IDENTITY(1,1) NOT NULL,
	[transaction_made_date] [date] NOT NULL,
	[username] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Transaction] PRIMARY KEY CLUSTERED 
(
	[transaction_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Cinema_Movie_Showing]  WITH CHECK ADD  CONSTRAINT [FK_Cinema_Movie_Showing_Cinema] FOREIGN KEY([cinema_id])
REFERENCES [dbo].[Cinema] ([cinema_id])
GO
ALTER TABLE [dbo].[Cinema_Movie_Showing] CHECK CONSTRAINT [FK_Cinema_Movie_Showing_Cinema]
GO
ALTER TABLE [dbo].[Cinema_Movie_Showing]  WITH CHECK ADD  CONSTRAINT [FK_Cinema_Movie_Showing_Movie_Showing] FOREIGN KEY([movie_showing_id])
REFERENCES [dbo].[Movie_Showing] ([movie_showing_id])
GO
ALTER TABLE [dbo].[Cinema_Movie_Showing] CHECK CONSTRAINT [FK_Cinema_Movie_Showing_Movie_Showing]
GO
ALTER TABLE [dbo].[Cinema_Performance_Number]  WITH CHECK ADD  CONSTRAINT [FK_Cinema_Performance_Number_Cinema] FOREIGN KEY([cinema_id])
REFERENCES [dbo].[Cinema] ([cinema_id])
GO
ALTER TABLE [dbo].[Cinema_Performance_Number] CHECK CONSTRAINT [FK_Cinema_Performance_Number_Cinema]
GO
ALTER TABLE [dbo].[Cinema_Performance_Number]  WITH CHECK ADD  CONSTRAINT [FK_Cinema_Performance_Number_Performance_Number] FOREIGN KEY([performance_number])
REFERENCES [dbo].[Performance_Number] ([performance_number])
GO
ALTER TABLE [dbo].[Cinema_Performance_Number] CHECK CONSTRAINT [FK_Cinema_Performance_Number_Performance_Number]
GO
ALTER TABLE [dbo].[GroupAccount]  WITH CHECK ADD  CONSTRAINT [FK_GroupAccount_account] FOREIGN KEY([username])
REFERENCES [dbo].[Account] ([username])
GO
ALTER TABLE [dbo].[GroupAccount] CHECK CONSTRAINT [FK_GroupAccount_account]
GO
ALTER TABLE [dbo].[GroupAccount]  WITH CHECK ADD  CONSTRAINT [FK_GroupAccount_Group] FOREIGN KEY([gid])
REFERENCES [dbo].[Group] ([gid])
GO
ALTER TABLE [dbo].[GroupAccount] CHECK CONSTRAINT [FK_GroupAccount_Group]
GO
ALTER TABLE [dbo].[GroupFeature]  WITH CHECK ADD  CONSTRAINT [FK_GroupFeature_Feature1] FOREIGN KEY([fid])
REFERENCES [dbo].[Feature] ([fid])
GO
ALTER TABLE [dbo].[GroupFeature] CHECK CONSTRAINT [FK_GroupFeature_Feature1]
GO
ALTER TABLE [dbo].[GroupFeature]  WITH CHECK ADD  CONSTRAINT [FK_GroupFeature_Group] FOREIGN KEY([gid])
REFERENCES [dbo].[Group] ([gid])
GO
ALTER TABLE [dbo].[GroupFeature] CHECK CONSTRAINT [FK_GroupFeature_Group]
GO
ALTER TABLE [dbo].[Movie]  WITH CHECK ADD  CONSTRAINT [FK_Movie_Movie_Rated] FOREIGN KEY([movie_rated])
REFERENCES [dbo].[Movie_Rated] ([movie_rated])
GO
ALTER TABLE [dbo].[Movie] CHECK CONSTRAINT [FK_Movie_Movie_Rated]
GO
ALTER TABLE [dbo].[Movie_Movie_Genre]  WITH CHECK ADD  CONSTRAINT [FK_Movie_Movie_Genre_Movie] FOREIGN KEY([movie_id])
REFERENCES [dbo].[Movie] ([movie_id])
GO
ALTER TABLE [dbo].[Movie_Movie_Genre] CHECK CONSTRAINT [FK_Movie_Movie_Genre_Movie]
GO
ALTER TABLE [dbo].[Movie_Movie_Genre]  WITH CHECK ADD  CONSTRAINT [FK_Movie_Movie_Genre_Movie_Genre] FOREIGN KEY([movie_genre_id])
REFERENCES [dbo].[Movie_Genre] ([movie_genre_id])
GO
ALTER TABLE [dbo].[Movie_Movie_Genre] CHECK CONSTRAINT [FK_Movie_Movie_Genre_Movie_Genre]
GO
ALTER TABLE [dbo].[Movie_Movie_Language]  WITH CHECK ADD  CONSTRAINT [FK_Movie_Movie_Language_Movie] FOREIGN KEY([movie_id])
REFERENCES [dbo].[Movie] ([movie_id])
GO
ALTER TABLE [dbo].[Movie_Movie_Language] CHECK CONSTRAINT [FK_Movie_Movie_Language_Movie]
GO
ALTER TABLE [dbo].[Movie_Movie_Language]  WITH CHECK ADD  CONSTRAINT [FK_Movie_Movie_Language_Movie_Language] FOREIGN KEY([movie_language_id])
REFERENCES [dbo].[Movie_Language] ([movie_language_id])
GO
ALTER TABLE [dbo].[Movie_Movie_Language] CHECK CONSTRAINT [FK_Movie_Movie_Language_Movie_Language]
GO
ALTER TABLE [dbo].[Movie_Movie_Showing]  WITH CHECK ADD  CONSTRAINT [FK_Movie_Movie_Showing_Movie] FOREIGN KEY([movie_id])
REFERENCES [dbo].[Movie] ([movie_id])
GO
ALTER TABLE [dbo].[Movie_Movie_Showing] CHECK CONSTRAINT [FK_Movie_Movie_Showing_Movie]
GO
ALTER TABLE [dbo].[Movie_Movie_Showing]  WITH CHECK ADD  CONSTRAINT [FK_Movie_Movie_Showing_Movie_Showing] FOREIGN KEY([movie_showing_id])
REFERENCES [dbo].[Movie_Showing] ([movie_showing_id])
GO
ALTER TABLE [dbo].[Movie_Movie_Showing] CHECK CONSTRAINT [FK_Movie_Movie_Showing_Movie_Showing]
GO
ALTER TABLE [dbo].[Row_Seat]  WITH CHECK ADD  CONSTRAINT [FK_Row_Seat_Cinema] FOREIGN KEY([cinema_id])
REFERENCES [dbo].[Cinema] ([cinema_id])
GO
ALTER TABLE [dbo].[Row_Seat] CHECK CONSTRAINT [FK_Row_Seat_Cinema]
GO
ALTER TABLE [dbo].[Ticket]  WITH CHECK ADD  CONSTRAINT [FK_Ticket_Movie] FOREIGN KEY([movie_id])
REFERENCES [dbo].[Movie] ([movie_id])
GO
ALTER TABLE [dbo].[Ticket] CHECK CONSTRAINT [FK_Ticket_Movie]
GO
ALTER TABLE [dbo].[Ticket]  WITH CHECK ADD  CONSTRAINT [FK_Ticket_Performance_Number] FOREIGN KEY([performance_number])
REFERENCES [dbo].[Performance_Number] ([performance_number])
GO
ALTER TABLE [dbo].[Ticket] CHECK CONSTRAINT [FK_Ticket_Performance_Number]
GO
ALTER TABLE [dbo].[Ticket]  WITH CHECK ADD  CONSTRAINT [FK_Ticket_Price_Listing] FOREIGN KEY([price_id])
REFERENCES [dbo].[Price] ([price_id])
GO
ALTER TABLE [dbo].[Ticket] CHECK CONSTRAINT [FK_Ticket_Price_Listing]
GO
ALTER TABLE [dbo].[Ticket]  WITH CHECK ADD  CONSTRAINT [FK_Ticket_Row_Seat] FOREIGN KEY([cinema_id], [row_number])
REFERENCES [dbo].[Row_Seat] ([cinema_id], [row_number])
GO
ALTER TABLE [dbo].[Ticket] CHECK CONSTRAINT [FK_Ticket_Row_Seat]
GO
ALTER TABLE [dbo].[Ticket]  WITH CHECK ADD  CONSTRAINT [FK_Ticket_Transaction] FOREIGN KEY([transaction_id])
REFERENCES [dbo].[Transaction] ([transaction_id])
GO
ALTER TABLE [dbo].[Ticket] CHECK CONSTRAINT [FK_Ticket_Transaction]
GO
ALTER TABLE [dbo].[Transaction]  WITH CHECK ADD  CONSTRAINT [FK_Transaction_Account] FOREIGN KEY([username])
REFERENCES [dbo].[Account] ([username])
GO
ALTER TABLE [dbo].[Transaction] CHECK CONSTRAINT [FK_Transaction_Account]
GO
USE [master]
GO
ALTER DATABASE [Threater_PRJ_Assignment] SET  READ_WRITE 
GO
