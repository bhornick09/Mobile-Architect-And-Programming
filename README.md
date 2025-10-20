# Mobile-Architect-And-Programming

---

**Briefly summarize the requirements and goals of the app you developed. What user needs was this app designed to address?
What screens and features were necessary to support user needs and produce a user-centered UI for the app? How did your UI designs keep users in mind? Why were your designs successful?**

* The goals/requirements I had when developing this application were: creating an app to increase health councious disclipline, implementing user login, storing user data in a database, and developing a well structured UI for user experience.  This app was designed to address individuals that want to monitor their weight (lose, gain, or maintain), and struggle to stay discliplined. I implemented notifications and plan to implement motivational reminders to keep users consistent. My UI is simple, as I wanted to make this process simple to encourage users to stay consistent. I have realized through personal experience that when something is easy to do, I tend to stay more consistent with it. If I make this journal extremely easy to use and interact with, it will take users just a tiny commitment daily to keep track of their weigh-ins.   

---

**How did you approach the process of coding your app? What techniques or strategies did you use? How could those techniques or strategies be applied in the future?**

* When approaching the coding of this application, I referred to official documents and Android best design practices. I repeatedly used these sources to ensure I was meeting industry standards in my code, and to ensure my code would be maintainable in the future. The technique of referring to official documentation while coding is always going to be a useful strategy I can apply when coding anything. When using these docs, you ensure you are creating code that meets standard practice, is maintainable, easy to read, and adaptable.

---

**How did you test to ensure your code was functional? Why is this process important, and what did it reveal?
Consider the full app design and development process from initial planning to finalization. Where did you have to innovate to overcome a challenge?**

* To ensure my code was functional, I used the emulator inside of Android Studio along with the Logcat feature to see live logs as the app was running. When I first implemented SQLite in my application, the app kept crashing as I tried to switch pages in the app. I then used Logcat the next time I ran the application and it showed exactly where the issue was stemming from, incorrect scene switching from the button presses. The full app design took lots of troubleshooting, not having worked with Android Studio before. I made many simple mistakes, like forgetting to add a button to escape the registration page leaving you to only create new accounts forever. When creating the functionality to add new rows to the weight-journal from SQLite I had to get extremely innovative. This process took many iterations, from formatting issues, issues accessing the database, needing a delete entry button, clearing the edit text fields after data submission, and many other things. 

---

**In what specific component of your mobile app were you particularly successful in demonstrating your knowledge, skills, and experience?**

* I believe creating the SQL queries and the Java coding overall demonstrates my prior knowledge in development and database interactions. My prior knowledge in Java allowed for easy data manipulation in the app once I figured out the unfamiliar syntax and structure of Android applications. My prior experience with SQL allowed me to quickly create queries to access the information I needed from the app's database. The combination of my SQL and Java skills allowed for database integration to the app, like updating the weight-journal table and creating a functional login page.

---
**Screenshots**
<p>
  <img width="300" height="700" alt="Login Screen" src="https://github.com/user-attachments/assets/61e6c555-b409-43f8-b760-12c6bc69cff0" />
  <img width="300" height="700" alt="Register" src="https://github.com/user-attachments/assets/f7189b17-63ef-4868-a990-de26386ec51c" />
  <img width="300" height="700" alt="journal" src="https://github.com/user-attachments/assets/798d2692-15e4-40b5-aa2e-cdb483565053" />
</p>


