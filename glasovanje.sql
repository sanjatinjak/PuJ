-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 26, 2019 at 11:58 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `glasovanje`
--

-- --------------------------------------------------------

--
-- Table structure for table `brojac_glasova`
--

CREATE TABLE `brojac_glasova` (
  `id` int(11) NOT NULL,
  `ime` varchar(80) NOT NULL,
  `prezime` varchar(80) NOT NULL,
  `jmbg` int(11) NOT NULL,
  `id_korisnika` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `brojac_glasova`
--

INSERT INTO `brojac_glasova` (`id`, `ime`, `prezime`, `jmbg`, `id_korisnika`) VALUES
(3, 'Ana', 'Anic', 1122221, 26),
(4, 'Pero', 'Peric', 19119704, 25);

-- --------------------------------------------------------

--
-- Table structure for table `glasovi`
--

CREATE TABLE `glasovi` (
  `id` int(11) NOT NULL,
  `izbor` varchar(100) NOT NULL,
  `id_kandidata` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `glasovi`
--

INSERT INTO `glasovi` (`id`, `izbor`, `id_kandidata`) VALUES
(2, 'Asvaltina Boto', 6),
(3, 'Marica Moro', 3),
(4, 'Asvaltina Boto', 6),
(5, 'Asvaltina Boto', 6),
(6, 'Marica Moro', 3),
(7, 'Marinko Vicko', 5),
(9, 'Marica Moro', 3),
(10, 'Petar Sosa', 1),
(11, 'Petar Sosa', 1),
(12, 'Marica Moro', 3);

-- --------------------------------------------------------

--
-- Table structure for table `kandidati`
--

CREATE TABLE `kandidati` (
  `id` int(11) NOT NULL,
  `kandidat` varchar(80) NOT NULL,
  `broj_glasova` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kandidati`
--

INSERT INTO `kandidati` (`id`, `kandidat`, `broj_glasova`) VALUES
(1, 'Petar Sosa', 2),
(2, 'Ivica Moro', 3),
(3, 'Marica Moro', 2),
(4, 'Stjepan Pezer', 1),
(5, 'Marinko Vicko', 1),
(6, 'Asvaltina Boto', 5);

-- --------------------------------------------------------

--
-- Table structure for table `korisnici`
--

CREATE TABLE `korisnici` (
  `id` int(11) NOT NULL,
  `korisnicko_ime` varchar(50) NOT NULL,
  `sifra` varchar(50) NOT NULL,
  `id_uloge` int(11) NOT NULL,
  `status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `korisnici`
--

INSERT INTO `korisnici` (`id`, `korisnicko_ime`, `sifra`, `id_uloge`, `status`) VALUES
(25, 'brojac2', 'e10adc3949ba59abbe56e057f20f883e', 2, NULL),
(26, 'brojac', '87ccbf8c9142244316bc78e1ac0c1e', 2, NULL),
(32, 'OhTpM', '87ccbf8c9142244316bc78e1ac0c1e', 1, NULL),
(34, 'ybdOm', '87ccbf8c9142244316bc78e1ac0c1e', 1, 'Glasovao'),
(36, 'JP6HH', '87ccbf8c9142244316bc78e1ac0c1e', 1, 'Glasovao'),
(53, 'glasac_9716', 'cd7e1a6f0f545e1d67c3921a03c6a3a3', 1, 'Glasovao'),
(54, 'glasac_3617', 'b47772a9e1fca653b0250a262323285d', 1, 'Glasovao'),
(55, 'glasac_9153', '3a1d645ce7eb9520b3dcb7e324e758b1', 1, 'Glasovao'),
(56, 'glasac_1733', 'e13067a2f420fde798f101b93adb6c08', 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `registrirani`
--

CREATE TABLE `registrirani` (
  `id` int(11) NOT NULL,
  `ime` varchar(100) NOT NULL,
  `prezime` varchar(100) NOT NULL,
  `jmbg` varchar(50) NOT NULL,
  `broj_osobne` varchar(50) NOT NULL,
  `grad` varchar(100) NOT NULL,
  `adresa` varchar(100) NOT NULL,
  `korisnik_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `registrirani`
--

INSERT INTO `registrirani` (`id`, `ime`, `prezime`, `jmbg`, `broj_osobne`, `grad`, `adresa`, `korisnik_id`) VALUES
(47, 'Sanja', 'Tinjak', '1122997345654', '323ee', 'Mostar', 'ulica AB', 32),
(49, 'Ime', 'Prezime', '0112982211881', '1E30T2', 'Mostar', 'adresa b1', 34),
(51, 'Ime', 'Prezime', '1411993155001', '1ETR0o', 'Mostar', 'adresa', 36),
(68, 'Renato', 'Roric', '1112958555007', '1Efr2', 'Mostar', 'kraljice Katarine 1', 53),
(69, 'Provjera', 'Provjera', '1102988144781', 'ewwr', 'Mostar', 'ulica 123', 54),
(70, 'Ana', 'Anic', '2002965423999', '1EF0R', 'Mostar', 'kralja Tomislava 20', 55),
(71, 'Test', 'Test', '1008965175201', '1EFR0P', 'Mostar', 'kralja Zvonimira a', 56);

-- --------------------------------------------------------

--
-- Table structure for table `vrste_korisnika`
--

CREATE TABLE `vrste_korisnika` (
  `id` int(11) NOT NULL,
  `naziv` varchar(50) NOT NULL,
  `opis` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vrste_korisnika`
--

INSERT INTO `vrste_korisnika` (`id`, `naziv`, `opis`) VALUES
(1, 'glasac', 'Registrira se, prijavljuje na sustav, odabere i pohrani svoj glas.'),
(2, 'brojac_glasova', 'Unaprijed odredena i upisana sluzbena osoba koja prati rezultate.');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `brojac_glasova`
--
ALTER TABLE `brojac_glasova`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_korisnika` (`id_korisnika`);

--
-- Indexes for table `glasovi`
--
ALTER TABLE `glasovi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_kandidata` (`id_kandidata`);

--
-- Indexes for table `kandidati`
--
ALTER TABLE `kandidati`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `korisnici`
--
ALTER TABLE `korisnici`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_uloge` (`id_uloge`);

--
-- Indexes for table `registrirani`
--
ALTER TABLE `registrirani`
  ADD PRIMARY KEY (`id`),
  ADD KEY `korisnik_id` (`korisnik_id`);

--
-- Indexes for table `vrste_korisnika`
--
ALTER TABLE `vrste_korisnika`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `brojac_glasova`
--
ALTER TABLE `brojac_glasova`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `glasovi`
--
ALTER TABLE `glasovi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `kandidati`
--
ALTER TABLE `kandidati`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `korisnici`
--
ALTER TABLE `korisnici`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT for table `registrirani`
--
ALTER TABLE `registrirani`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- AUTO_INCREMENT for table `vrste_korisnika`
--
ALTER TABLE `vrste_korisnika`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `brojac_glasova`
--
ALTER TABLE `brojac_glasova`
  ADD CONSTRAINT `brojac_glasova_ibfk_1` FOREIGN KEY (`id_korisnika`) REFERENCES `korisnici` (`id`);

--
-- Constraints for table `glasovi`
--
ALTER TABLE `glasovi`
  ADD CONSTRAINT `glasovi_ibfk_1` FOREIGN KEY (`id_kandidata`) REFERENCES `kandidati` (`id`);

--
-- Constraints for table `korisnici`
--
ALTER TABLE `korisnici`
  ADD CONSTRAINT `korisnici_ibfk_1` FOREIGN KEY (`id_uloge`) REFERENCES `vrste_korisnika` (`id`);

--
-- Constraints for table `registrirani`
--
ALTER TABLE `registrirani`
  ADD CONSTRAINT `registrirani_ibfk_1` FOREIGN KEY (`korisnik_id`) REFERENCES `korisnici` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
