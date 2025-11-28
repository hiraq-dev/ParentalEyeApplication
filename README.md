# **Parental Eye – Android Monitoring Application**

## 📌 **Overview**

Parental Eye is an Android-based parental control application that monitors device usage and sends automated SMS alerts when certain applications are opened. It helps parents stay informed about their children's mobile activities.

---

## ⭐ **Features**

### 🔐 **Security & Authentication**

* **Password Registration:** Create a secure password during first-time setup.
* **Login System:** Protects access to monitoring features.
* **Session Management:** Automatically logs out when the app is closed.

### 📱 **Application Monitoring**

* **Real-time App Detection:** Continuously monitors active applications.
* **Customizable Alerts:** Send SMS notifications for selected apps.
* **Installed App List:** Spinner auto-populates all installed apps.

### 💬 **SMS Notifications**

* **Automated Messaging:** SMS alerts are triggered when monitored apps open.
* **Custom Templates:** Editable alert messages with app name & time.
* **Contact Management:** Add specific mobile numbers for notifications.

### 📊 **Data Management**

* **SQLite Database:** Stores rules, history, and configuration.
* **CRUD Operations:** Add, view, update, and remove monitoring rules.
* **History Tracking:** Review all monitoring events and logs.

### ⚙️ **Background Services**

* **Alarm Manager:** Periodic checks every 5 seconds.
* **Broadcast Receivers:** Detects app usage & triggers SMS.
* **Persistent Monitoring:** Works even when the app is minimized.

---

## 🏗️ **Technical Architecture**

### 📂 **Core Components**

#### **Activities**

* **RegisterActivity:** Handles password creation.
* **LoginActivity:** User authentication.
* **MainActivity:** Configure monitoring rules.
* **ViwAllActivity:** Displays logs and history.

#### **Background Services**

* **CameraService:** Initializes continuous monitoring.
* **CameraService2:** Detects running applications.
* **SMSClass:** Handles SMS sending tasks.

#### **Database**

`DataBaseHelper` manages three tables:

* **entry** – Stores monitoring settings.
* **logtable** – Logs all monitoring activities.
* **register** – Stores user password.

---

## 👨‍💻 **Key Classes & Their Functions**

### **MainActivity**

* UI for app selection & rule creation.
* Validates input and triggers services.

### **DataBaseHelper**

* Manages SQLite operations.
* Handles password verification.

### **Broadcast Receivers**

* Schedule periodic monitoring.
* Detect app usage & fire SMS alerts.

---

## 🛠️ **Setup & Installation**

### **Prerequisites**

* Android Studio
* Minimum SDK: **API 16 (Android 4.1)**
* SMS permissions enabled

### **Installation Steps**

1. Clone the repository.
2. Open the project in Android Studio.
3. Build and run on a device/emulator.

### **First-Time Setup**

1. Launch the app.
2. Create a secure password.
3. Configure rules:

   * Choose an application from the spinner.
   * Enter phone number.
   * Write alert message.
   * Save the rule.

---

## 🔐 **Permissions Required**

* `READ_PHONE_STATE`
* `SEND_SMS`
* `RECEIVE_BOOT_COMPLETED`

---

## 📘 **Usage Guide**

### ➕ **Adding Monitoring Rules**

1. Login to the app.
2. Select an application.
3. Add contact number.
4. Enter alert message.
5. Click **Add**.

### 📜 **Viewing History**

* Access via the menu (three dots).
* View, update, or modify existing rules.

### 📩 **SMS Alert Format**

```text
[Custom Message] This app currently open: [App Name] at [Timestamp]
```

---

## 📁 **Code Structure**

```
src/
├── registerActivity.java      # Password setup
├── LoginActivity.java          # Authentication
├── MainActivity.java           # Monitoring rule configuration
├── ViwAllActivity.java         # History view
├── CameraService.java          # Alarm initialization
├── CameraService2.java         # Application detection service
├── SMSClass.java               # SMS triggering
└── DataBase
```
