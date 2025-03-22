# 🌍 Wikimedia Changes Project

## 📌 Overview
This project captures real-time changes from Wikimedia and stores them in **MySQL** and **Elasticsearch** using **Apache Kafka** and **Spring Boot**.  

## 🏗️ Tech Stack
- **Java (Spring Boot)**
- **Apache Kafka**
- **MySQL**
- **Elasticsearch**
- **Kibana**

## 🔄 Data Flow
1️⃣ **Kafka Consumer** listens to the `wikimedia_recentchange` topic.  
2️⃣ **Raw JSON data** is received and stored.  
3️⃣ **Data is saved in MySQL and Elasticsearch as a full JSON string**.  

## 🗄️ Database Structure
### **MySQL Table**
```sql
CREATE TABLE wikimedia_changes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    raw_json TEXT
);
