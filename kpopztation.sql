-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 10, 2023 at 03:54 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kpopztation`
--

-- --------------------------------------------------------

--
-- Table structure for table `albums`
--

CREATE TABLE `albums` (
  `AlbumID` char(5) NOT NULL,
  `AlbumName` varchar(50) NOT NULL,
  `AlbumPrice` int(11) NOT NULL,
  `AlbumStock` int(11) NOT NULL,
  `ArtistID` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `albums`
--

INSERT INTO `albums` (`AlbumID`, `AlbumName`, `AlbumPrice`, `AlbumStock`, `ArtistID`) VALUES
('AL191', 'gggggg', 9999, 20, 'AR483'),
('AL398', 'Twicetagram', 500000, 350, 'AR355'),
('AL601', 'Gigaaa', 900, 16, 'AR702'),
('AL833', 'CHAT-SHIRE', 250000, 100, 'AR702'),
('AL846', 'LILAC', 300000, 500, 'AR702');

-- --------------------------------------------------------

--
-- Table structure for table `artists`
--

CREATE TABLE `artists` (
  `ArtistID` char(5) NOT NULL,
  `ArtistName` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `artists`
--

INSERT INTO `artists` (`ArtistID`, `ArtistName`) VALUES
('AR000', 'Yoasobai'),
('AR355', 'Twice'),
('AR483', 'Aespa'),
('AR521', 'Treasure'),
('AR702', 'IU');

-- --------------------------------------------------------

--
-- Table structure for table `carts`
--

CREATE TABLE `carts` (
  `UserID` char(5) NOT NULL,
  `AlbumID` char(5) NOT NULL,
  `Qty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `detailtransactions`
--

CREATE TABLE `detailtransactions` (
  `TransactionID` char(5) NOT NULL,
  `AlbumID` char(5) NOT NULL,
  `Qty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `detailtransactions`
--

INSERT INTO `detailtransactions` (`TransactionID`, `AlbumID`, `Qty`) VALUES
('TR096', 'AL601', 1),
('TR096', 'AL846', 1),
('TR122', 'AL846', 5),
('TR335', 'AL833', 3),
('TR335', 'AL846', 2),
('TR456', 'AL846', 2),
('TR459', 'AL833', 6),
('TR459', 'AL846', 2),
('TR613', 'AL191', 3),
('TR676', 'AL833', 2),
('TR736', 'AL398', 2),
('TR781', 'AL846', 2),
('TR789', 'AL191', 1),
('TR789', 'AL601', 2),
('TR862', 'AL398', 2),
('TR862', 'AL833', 2),
('TR862', 'AL846', 4),
('TR907', 'AL398', 5),
('TR907', 'AL833', 108),
('TR942', 'AL833', 2),
('TR942', 'AL846', 0);

-- --------------------------------------------------------

--
-- Table structure for table `headertransactions`
--

CREATE TABLE `headertransactions` (
  `TransactionID` char(5) NOT NULL,
  `UserID` char(5) NOT NULL,
  `TransactionDate` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `headertransactions`
--

INSERT INTO `headertransactions` (`TransactionID`, `UserID`, `TransactionDate`) VALUES
('TR096', 'US999', '2023-01-10'),
('TR122', 'US977', '2022-05-12'),
('TR335', 'US999', '2022-12-23'),
('TR456', 'US999', '2022-12-23'),
('TR459', 'US977', '2022-05-12'),
('TR613', 'US598', '2023-01-05'),
('TR676', 'US977', '2022-05-12'),
('TR736', 'US977', '2022-05-12'),
('TR781', 'US999', '2022-12-23'),
('TR789', 'US174', '2022-12-23'),
('TR862', 'US977', '2022-05-12'),
('TR907', 'US999', '2022-12-23'),
('TR942', 'US999', '2022-12-23');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `UserID` char(5) NOT NULL,
  `UserName` varchar(50) NOT NULL,
  `UserPassword` varchar(50) NOT NULL,
  `UserEmail` varchar(50) NOT NULL,
  `UserAddress` varchar(255) NOT NULL,
  `UserGender` varchar(50) NOT NULL,
  `UserRole` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserID`, `UserName`, `UserPassword`, `UserEmail`, `UserAddress`, `UserGender`, `UserRole`) VALUES
('a', 'a', 'a', 'a', 'aaa Street', 'Male', 'Admin'),
('US030', 'Admin', 'admin123', 'admin@admin.com', 'admin home Street', 'Male', 'Admin'),
('US174', 'satu dua tiga', 'satudua', 'satu@mail.com', 'satu Street', 'Male', 'User'),
('US598', 'Darren Gatneg', 'akuganteng', 'darreng.ganteng@mail.com', 'alskdjsaldkjasdl k Street', 'Male', 'User'),
('US977', 'keziaa', 'kezia123', 'kez@mail.com', 'apple Street', 'Female', 'User'),
('US999', 'b', 'b', 'b', 'b', 'Female', 'User');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `albums`
--
ALTER TABLE `albums`
  ADD PRIMARY KEY (`AlbumID`),
  ADD KEY `Constraint_Artist_Album` (`ArtistID`);

--
-- Indexes for table `artists`
--
ALTER TABLE `artists`
  ADD PRIMARY KEY (`ArtistID`);

--
-- Indexes for table `carts`
--
ALTER TABLE `carts`
  ADD PRIMARY KEY (`UserID`,`AlbumID`),
  ADD KEY `Constraint_Cart_Product` (`AlbumID`);

--
-- Indexes for table `detailtransactions`
--
ALTER TABLE `detailtransactions`
  ADD PRIMARY KEY (`TransactionID`,`AlbumID`),
  ADD KEY `Constraint_DetailTransaction_Album` (`AlbumID`);

--
-- Indexes for table `headertransactions`
--
ALTER TABLE `headertransactions`
  ADD PRIMARY KEY (`TransactionID`),
  ADD KEY `Constraint_User_Transaction` (`UserID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`UserID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `albums`
--
ALTER TABLE `albums`
  ADD CONSTRAINT `Constraint_Artist_Album` FOREIGN KEY (`ArtistID`) REFERENCES `artists` (`ArtistID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `carts`
--
ALTER TABLE `carts`
  ADD CONSTRAINT `Constraint_Cart_Product` FOREIGN KEY (`AlbumID`) REFERENCES `albums` (`AlbumID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Constraint_Cart_User` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `detailtransactions`
--
ALTER TABLE `detailtransactions`
  ADD CONSTRAINT `Constraint_DetailTransaction_Album` FOREIGN KEY (`AlbumID`) REFERENCES `albums` (`AlbumID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Constraint_Transactions` FOREIGN KEY (`TransactionID`) REFERENCES `headertransactions` (`TransactionID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `headertransactions`
--
ALTER TABLE `headertransactions`
  ADD CONSTRAINT `Constraint_User_Transaction` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
