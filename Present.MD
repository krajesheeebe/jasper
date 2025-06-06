Here’s your refined 5-minute presentation script for the **Mission Citiverse** demo:

---

**\[Slide/Title: Team Introduction]**
Good \[morning/afternoon], everyone.
We are **Team Citinauts**, and we’re excited to present our solution — **Mission Citiverse**.

---

**\[Slide: The Problem]**
As we all know, Citi’s current new hire onboarding process relies heavily on traditional classroom sessions — filled with presentations and lengthy documents.
This results in:

* **Time-consuming sessions** lasting up to two days
* **High costs**, engaging multiple employees
* **Low engagement**, since it's passive learning
* And a **poor first impression** for new hires

Clearly, it's time for a change.

---

**\[Slide: The Solution]**
Introducing **Mission Citiverse** — a **space-themed gamified onboarding platform**.

New hires become **Citinauts** — they create avatars and journey through space, hopping across different planets.
Each planet represents a module or area of learning.
They complete challenges, collect rewards, and unlock achievements, ultimately arriving at their destination — **Planet Citi HQ**.

---

**\[Slide: Application Demo – Flow Overview]**
Let’s take you through the flow of our application.

It all begins with **preboarding** — once a new hire has accepted their offer and has a confirmed joining date.
The **HR team** launches the Mission Citiverse app to get started.

---

**\[Slide: Login Page]**
We start with a **common login screen** for both HR and new hires.
You’ll notice:

* **Astronauts floating**
* **Twinkling stars**
* **Suspenseful ambient music**

All designed to immerse the user in a space-themed experience from the very beginning.

The **HR clicks on the “HR Login” tab**, and enters their **SSO credentials**.
We’ve integrated with **Platform AssistedAuth API** to authenticate Citi users.
For this demo, anyone with valid Citi SSO can log in.
This can also be enhanced to validate **Citi AZ LDAP roles** — ensuring only authorized HR members gain access.

---

**\[Slide: HR Welcome Screen]**
Upon successful login, the HR is welcomed with the message:
**"Welcome to Mission Citiverse Admin"**.

On the top right, we fetch and display the HR’s full name from **Citi’s Global Directory**, using their SOEID.

The dashboard has three sections:

1. **Preboard New Hires**
2. **Track Progress**
3. **Quick Stats** — showing total modules, number of preboarded, registered, and fully onboarded new hires.

---

**\[Slide: Preboarding New Hires]**
Now, when the HR clicks **Preboard**, they’re taken to a form.

Here, they fill in:

* First Name
* Last Name
* Email Address
* SOEID (if available)

If an SOEID isn’t available yet, the system generates a temporary user ID.

Next is the **Batch Name** — since new hires are typically onboarded in groups.
Once the batch is selected, the **start date** is auto-filled.
(We can easily reverse this logic too — select date → auto-assign batch.)

Then comes one of our **unique features** — assigning **custom modules** to each user.
This enables **role-based onboarding**.
For instance, a TTS hire would need different modules than someone joining PBWM.

Once done, the HR clicks **Preboard User**.
The system generates credentials and stores the new hire’s data.
A **welcome email** is sent instantly.

On the right, the **Recently Preboarded** section updates in real-time — showing the new hire's details, number of modules assigned, joining date, HR’s name, and the email status.

---

**\[Slide: Bulk Preboarding]**
To simplify onboarding for larger batches, we also support **bulk preboarding**.

Instead of manually entering each new hire's details, the HR can **upload an Excel file** containing all the necessary data.
We’ve provided a **sample Excel template**, downloadable from the app itself, to ensure the correct format.

The columns include fields like name, email, SOEID, batch name, start date, and module selections.
HRs can simply copy and paste from their existing trackers into this sheet, then upload and click **Bulk Preboard**.

As you can see here — once the upload is complete and the button is clicked — all the new hires from the file are preboarded in one go.

---

**\[Slide: Welcome Email]**
After successful preboarding, each new hire receives a **welcome email**.

It includes:

* A short briefing on **Mission Citiverse**
* Their **User ID and Password**
* Their **joining/start date**
* A **“Launch Mission” button** to enter the app

For this MVP, we’re using the **User ID and Password** approach for simplicity.
However, we plan to enhance this with **secure embedded auth tokens**, so the button logs them in directly.
We can also layer in a **second-level verification**, such as PAN number or date of birth — just in case the email is misdelivered.

---

**\[Slide: New Hire Experience – Login & Countdown]**
Let’s now shift to the **new hire journey**.

When the new hire clicks **Launch Mission**, they are taken to the **login screen**.
They copy their credentials from the email and log in.

If they try logging in **before their official start date**, they won’t enter the mission yet.
Instead, they’ll see a **countdown screen**, continuing the space theme —
with a **spaceshuttle image ready for launch** and a live countdown to their start date.

Once the countdown completes, the shuttle **launches in an animated sequence** with realistic propulsion effects.
A **“Start Mission” button** then appears for them to proceed.

---

**\[Slide: Registration & Avatar Creation]**
After clicking **Start Mission**, they are taken to the **registration screen** —
which digitally replaces the traditional manual attendance process.

Here:

* Their name is **pre-filled**
* They can **select an avatar** — their “Citinaut” identity
* Enter additional details like **phone number, location, and org name**

These fields can be tailored in future based on what HR wants to collect.
Once done, they **confirm their registration** and click **Launch**.

---

**\[Slide: Mission Control – Dashboard View]**
Now, they land on the **Mission Control screen** —
the central dashboard showing all the **planets/modules** in their journey toward **Planet Citi HQ**.

The first planet in their path is **highlighted with a blue glow**, indicating it's ready.
Others appear as **locked**.

Hovering over each planet shows:

* A **short description** of the content
* Its **completion status**

This gives the new hire a clear visual path of their onboarding.

---

**\[Slide: Planet Modules – Custom Learning Content]**
Clicking a planet takes the user to the **module screen**.

Each planet can contain multiple **submodules**.
And here’s the best part — our app is designed to be **highly modular and customizable**.

It supports a wide range of content:

* **Formatted text** (headings, subheadings, paragraphs)
* **PDFs**
* **PPTs**
* **Videos**
* Even **interactive content** like quizzes or simulations

It’s similar to what you'd expect from a Learning Management System (LMS), but gamified and immersive.

---

**\[Slide: Interactive Module Example – Planet Visio]**
For example, **Planet Visio** contains modules like:

* **Citi Overview**
* **Meet & Greet with Senior Leaders**

It begins with an **icebreaker activity** featuring a character called **Vizbot**.

Vizbot introduces the planet’s theme and engages the user with **a few fun quiz questions**.
If they answer correctly, Vizbot responds with a **positive message** and awards a **badge**.
If not, Vizbot provides **encouraging feedback** and shares the right answer before proceeding.

This ensures the onboarding is **interactive**, **engaging**, and **knowledge-driven** — rather than passive learning.

---

