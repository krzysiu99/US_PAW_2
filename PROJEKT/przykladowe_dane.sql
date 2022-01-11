-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 11 Sty 2022, 09:30
-- Wersja serwera: 10.4.21-MariaDB
-- Wersja PHP: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `jee`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `komentarz`
--

CREATE TABLE `komentarz` (
  `KID` int(11) NOT NULL,
  `autor` int(11) DEFAULT NULL,
  `post` int(11) NOT NULL,
  `czas_publikacji` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `tresc` varchar(255) COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `komentarz`
--

INSERT INTO `komentarz` (`KID`, `autor`, `post`, `czas_publikacji`, `tresc`) VALUES
(17, 6, 21, '2022-1-11', 'Wow! Dziękuję za świetny post'),
(18, 6, 24, '2022-1-11', 'Chętnie zobaczyłbym więcej takich postów'),
(19, 5, 24, '2022-1-11', 'Więcej postów już wkrótce');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `kontakt`
--

CREATE TABLE `kontakt` (
  `WID` int(11) NOT NULL,
  `nadawca` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `tresc` text COLLATE utf8_polish_ci NOT NULL,
  `czas_publikacji` varchar(11) COLLATE utf8_polish_ci NOT NULL,
  `czy_przeczytano` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `kontakt`
--

INSERT INTO `kontakt` (`WID`, `nadawca`, `tresc`, `czas_publikacji`, `czy_przeczytano`) VALUES
(7, 'Jan Kowalski', 'Bardzo prosze o ustanowienie mnie autorem, bym mógł współtworzyć tą piękną stronę.', '2021-12-22', 1),
(8, 'Pan Piotr', 'Dzień dobry, można zostać autorem postów?', '2022-1-11', 0),
(9, 'Kamil', 'Świetna stronka. Pozdrawiam!', '2022-1-11', 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `post`
--

CREATE TABLE `post` (
  `PID` int(11) NOT NULL,
  `autor` int(11) DEFAULT NULL,
  `tytul` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `kategoria` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `tresc` longtext COLLATE utf8_polish_ci NOT NULL,
  `data_publikacji` varchar(11) COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `post`
--

INSERT INTO `post` (`PID`, `autor`, `tytul`, `kategoria`, `tresc`, `data_publikacji`) VALUES
(20, 4, 'informacja', 'Windows', 'Informuję że większość postów z kategorii Windows dotyczyć będzie systemu Windows 10.\r\nDlatego zanim wykonacie coś według naszych porad upewnijcie się jaką wersję systemu macie.\r\n\r\nPozdrawiam\r\nAdministrator portalu Porady Informatyczne', '2021-12-21'),
(21, 5, 'Emotikony', 'Windows', 'Chcesz móc dodawać emotikony na swoim Windows 10 tak jak na telefonie?\r\nSystem ten oferuje wbudowaną przeglądarkę emotek które możesz wstawić wszędzie!\r\nAby uruchomić przeglądarkę naciśnij: Windows+kropka\r\nTeraz możesz wysyłać znajomym tyle emotek ile chcesz!', '2022-1-10'),
(23, 5, 'Ukryte ustawienia', 'Google Chrome', 'Chciałbyś dostosować swoją przeglądarkę Google Chrome? Usunąć opcję dodaj do Przeczytania?\r\nMożesz to zrobić w ukrytych ustawieniach przeglądarki, aby je uruchomić wpisz w pasek adresu: chrome://flags/', '2022-1-11'),
(24, 5, 'Gra w dinozaura', 'Google Chrome', 'Chcesz pograć w słynną grę z dinozaurem skaczącym przez przeszkody ale nie chcesz wyłączać sobie internetu?\r\nTa gra jest dostępna w przeglądarce pod jednym z specjalnych adresów wewnętrznych.\r\nAby ją uruchomić wpisz w pasek adresu: chrome://dino/', '2022-1-11'),
(25, 5, 'Strony wewnętrzne', 'Google Chrome', 'Przeglądarka Google Chrome ma znacznie więcej ukrytych stron wewnętrznych niż te z poprzednich artykułów.\r\nAby odkryć je wszystkie wpisz w pasek adresu: chrome://chrome-urls/\r\nJedną z ciekawszych stron jest: chrome://omnibox/', '2022-1-11'),
(26, 5, 'Wycinek ekranu', 'Windows', 'W systemie Windows 10 możesz \"wyciąć\" kawałek ekranu który automatycznie skopiuje się jako obraz do schowka.\r\nAby to zrobić naciśnij: Windows+Shift+s następnie zaznacz obszar który chcesz wyciąć,\r\npo zaznaczeniu fragment sam zapisze się w schowku i można go wkleić za pomocą control+v.', '2022-1-11'),
(27, 5, 'Pojemniejszy schowek', 'Windows', 'chcesz mieć możliwość skopiowania wielu elementów zamiast kopiując i wklejać pojedynczo?\r\nSkopiuj wszystkie elementy normalnie a przy wklejaniu użyj: Windows+v a zobaczysz listę wszystkich skopiowanych rzeczy i klikając na nie możesz je wkleić.', '2022-1-11'),
(28, 4, 'Zostań Autorem!', 'Excel', 'Chcesz zostać autorem postów na stronie?\r\nZapraszam do kontaktu: admin@blog.local\r\nSzczególnie poszukuję osób, które będą tworzyć posty w kategorii: Excel.\r\nPozdrawiam', '2022-1-11');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownik`
--

CREATE TABLE `uzytkownik` (
  `UID` int(11) NOT NULL,
  `nick` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `haslo` text COLLATE utf8_polish_ci NOT NULL,
  `data_rejestracji` date NOT NULL,
  `rola` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `uzytkownik`
--

INSERT INTO `uzytkownik` (`UID`, `nick`, `haslo`, `data_rejestracji`, `rola`) VALUES
(4, 'admin', '12345', '2021-12-21', 0),
(5, 'Jan Kowalski', '12345', '2021-12-22', 1),
(6, 'Adam Nowak', '12345', '2022-01-11', 2);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `komentarz`
--
ALTER TABLE `komentarz`
  ADD PRIMARY KEY (`KID`),
  ADD KEY `user_kom` (`autor`),
  ADD KEY `kom_post` (`post`);

--
-- Indeksy dla tabeli `kontakt`
--
ALTER TABLE `kontakt`
  ADD PRIMARY KEY (`WID`);

--
-- Indeksy dla tabeli `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`PID`),
  ADD KEY `user_post` (`autor`);

--
-- Indeksy dla tabeli `uzytkownik`
--
ALTER TABLE `uzytkownik`
  ADD PRIMARY KEY (`UID`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `komentarz`
--
ALTER TABLE `komentarz`
  MODIFY `KID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT dla tabeli `kontakt`
--
ALTER TABLE `kontakt`
  MODIFY `WID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT dla tabeli `post`
--
ALTER TABLE `post`
  MODIFY `PID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT dla tabeli `uzytkownik`
--
ALTER TABLE `uzytkownik`
  MODIFY `UID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `komentarz`
--
ALTER TABLE `komentarz`
  ADD CONSTRAINT `kom_post` FOREIGN KEY (`post`) REFERENCES `post` (`PID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user_kom` FOREIGN KEY (`autor`) REFERENCES `uzytkownik` (`UID`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `user_post` FOREIGN KEY (`autor`) REFERENCES `uzytkownik` (`UID`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
