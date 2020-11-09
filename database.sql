-- phpMyAdmin SQL Dump
-- version 2.7.0-pl2
-- http://www.phpmyadmin.net
-- 
-- Servidor: oraclepr.uco.es
-- Tiempo de generación: 09-11-2020 a las 12:26:27
-- Versión del servidor: 5.1.73
-- Versión de PHP: 5.3.3
-- 
-- Base de datos: `i82nafea`
-- 

-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `Post`
-- 

CREATE TABLE `Post` (
  `id` int(11) NOT NULL,
  `title` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `body` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `createdAt` datetime NOT NULL,
  `publishedAt` datetime NOT NULL,
  `unavailableAfter` datetime NOT NULL,
  `owner` int(11) NOT NULL,
  `sentTo` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `type` enum('general','themed','targeted','flash') COLLATE utf8_spanish_ci NOT NULL,
  `state` enum('edited','waiting','published','deleted') COLLATE utf8_spanish_ci NOT NULL,
  `topics` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `isDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- 
-- Volcar la base de datos para la tabla `Post`
-- 


-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `Topic`
-- 

CREATE TABLE `Topic` (
  `id` int(11) NOT NULL,
  `topic` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- 
-- Volcar la base de datos para la tabla `Topic`
-- 


-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `User`
-- 

CREATE TABLE `User` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `lastName` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `interests` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `role` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `isDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=1 ;

-- 
-- Volcar la base de datos para la tabla `User`
-- 
