-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 25, 2023 at 04:08 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_library`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_anggota`
--

CREATE TABLE `tbl_anggota` (
  `id_anggota` varchar(6) CHARACTER SET latin1 NOT NULL,
  `nim` varchar(15) CHARACTER SET latin1 NOT NULL,
  `nama` varchar(30) CHARACTER SET latin1 NOT NULL,
  `jk` varchar(10) CHARACTER SET latin1 NOT NULL,
  `id_tingkat` int(3) NOT NULL,
  `kd_jurusan` varchar(10) CHARACTER SET latin1 NOT NULL,
  `id_kelas` int(2) NOT NULL,
  `nope` varchar(13) CHARACTER SET latin1 NOT NULL,
  `status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_anggota`
--

INSERT INTO `tbl_anggota` (`id_anggota`, `nim`, `nama`, `jk`, `id_tingkat`, `kd_jurusan`, `id_kelas`, `nope`, `status`) VALUES
('A00001', '120121003', 'Damasya', 'WANITA', 2, 'SI', 2, '088228587241', 'AKTIF'),
('A00003', '120121023', 'Jimmy', 'PRIA', 1, 'RPL', 1, '1442344', 'AKTIF');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_buku`
--

CREATE TABLE `tbl_buku` (
  `kodeBuku` varchar(100) NOT NULL,
  `judulBuku` varchar(100) NOT NULL,
  `pengarangBuku` varchar(100) NOT NULL,
  `penerbitBuku` varchar(100) NOT NULL,
  `genreBuku` varchar(100) NOT NULL,
  `jumlahBuku` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_buku`
--

INSERT INTO `tbl_buku` (`kodeBuku`, `judulBuku`, `pengarangBuku`, `penerbitBuku`, `genreBuku`, `jumlahBuku`) VALUES
('A01', 'Tutorial Java', 'Faqih', 'Simo Pub', 'Tutorial', 9),
('A02', 'Tutorial Android', 'Faqih', 'Simo Pub', 'Resep', 3);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_jurusan`
--

CREATE TABLE `tbl_jurusan` (
  `kd_jurusan` varchar(10) CHARACTER SET latin1 NOT NULL,
  `jurusan` varchar(30) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_jurusan`
--

INSERT INTO `tbl_jurusan` (`kd_jurusan`, `jurusan`) VALUES
('RPL', 'Rekayasa Perangkat Lunak'),
('SI', 'Sistem Informasi'),
('TE', 'Teknik Elektro'),
('TI', 'Teknologi Informasi'),
('TK', 'Teknik Komputer');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_kelas`
--

CREATE TABLE `tbl_kelas` (
  `id_kelas` int(2) NOT NULL,
  `kelas` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_kelas`
--

INSERT INTO `tbl_kelas` (`id_kelas`, `kelas`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_tingkat`
--

CREATE TABLE `tbl_tingkat` (
  `id_tingkat` int(1) NOT NULL,
  `tingkat` varchar(3) CHARACTER SET utf8mb4 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_tingkat`
--

INSERT INTO `tbl_tingkat` (`id_tingkat`, `tingkat`) VALUES
(1, 'X'),
(2, 'XI'),
(3, 'XII');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_transaksi`
--

CREATE TABLE `tbl_transaksi` (
  `kodeTransaksi` varchar(20) NOT NULL,
  `peminjam` varchar(100) NOT NULL,
  `buku` varchar(100) NOT NULL,
  `tglAwalPinjam` date NOT NULL,
  `batasPinjam` date NOT NULL,
  `denda` int(10) NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_transaksi`
--

INSERT INTO `tbl_transaksi` (`kodeTransaksi`, `peminjam`, `buku`, `tglAwalPinjam`, `batasPinjam`, `denda`, `status`) VALUES
('123', 'Damasya', 'Tutorial Android', '2023-01-25', '2023-02-05', 0, 'Pinjam');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE `tbl_user` (
  `id_user` int(11) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `level` varchar(15) CHARACTER SET utf8mb4 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`id_user`, `nama`, `username`, `password`, `level`) VALUES
(1, 'asep', 'asep', 'asep', 'kepala'),
(2, 'budi', 'budi', 'budi', 'pustakawan');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_anggota`
--
ALTER TABLE `tbl_anggota`
  ADD PRIMARY KEY (`id_anggota`);

--
-- Indexes for table `tbl_buku`
--
ALTER TABLE `tbl_buku`
  ADD PRIMARY KEY (`kodeBuku`);

--
-- Indexes for table `tbl_jurusan`
--
ALTER TABLE `tbl_jurusan`
  ADD PRIMARY KEY (`kd_jurusan`);

--
-- Indexes for table `tbl_kelas`
--
ALTER TABLE `tbl_kelas`
  ADD PRIMARY KEY (`id_kelas`);

--
-- Indexes for table `tbl_tingkat`
--
ALTER TABLE `tbl_tingkat`
  ADD PRIMARY KEY (`id_tingkat`);

--
-- Indexes for table `tbl_transaksi`
--
ALTER TABLE `tbl_transaksi`
  ADD PRIMARY KEY (`kodeTransaksi`);

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_kelas`
--
ALTER TABLE `tbl_kelas`
  MODIFY `id_kelas` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `tbl_tingkat`
--
ALTER TABLE `tbl_tingkat`
  MODIFY `id_tingkat` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
