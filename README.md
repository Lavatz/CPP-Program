# CPP-Program

#CS-210

#Summarize the project and what problem it was solving.

  The project was to create an object-oriented programming (OOP) application that met the given requirements, followed the given best practice for coding style, and included in-line comments so the code was organized and changes could easily be made by another person.

  The application was to be created to help high school students learn about fiscal responsibility and show users how their investments would grow over time with compound interest.

#What did you do particularly well?

  I liked the way the interface looked. I feel there wasn't a lot in the ways I could be creative with this project.

#Where could you enhance your code? How would these improvements make your code more efficient, secure, and so on?

  If possible, I would make a GUI for this project. Something that would update as you replaced numbers and allow the mouse to be used. I'd clean up the code with some header files as well.


#Which pieces of the code did you find most challenging to write, and how did you overcome this? What tools or resources are you adding to your support network?

  The challenging part for me at the time was creating a table that could span multiple years. Looking through the C++ library and google to narrow the library search are my go to tools.


#What skills from this project will be particularly transferable to other projects or course work?

  Creating a readable interface was what I took most from this project. Giving results to users is easy but giving multiple results at once could be disastrous.


#How did you make this program maintainable, readable, and adaptable?

  Following standard code practice and style and adding comments next to more difficult or confusing code should minimize the amount of time it would take to update or maintain a program.

#CS-230

#Briefly summarize The Gaming Room client and their software requirements. Who was the client? What type of software did they want you to design?

  The problem presented is there is no agreed upon environment to create a web-based version of the game Draw It or Lose It. The Gaming Room team was the client and had the following requirements:
    -Each game has the ability to run with one or more teams
    -Each team will have multiple players assigned to it
    -The names of lobbies and team names must be unique and known to players at the time of name creation
    -Only one instance of the game can exist in memory at any given time, achieved through a Singleton pattern design

#What did you do particularly well in developing this documentation?

  I thought I did particularly well in explaining the domain model and why it satisfies the requirements from the Gaming Room team.

#What about the process of working through a design document did you find helpful when developing the code?

  Now that I understand it, the UML diagram for the domain model is very helpful for naming and verifying code.

#If you could choose one part of your work on these documents to revise, what would you pick? How would you improve it?
  
  Probably the recommendations section. Cloud servers are useful for any person or company so that's what I would have recommended if we aren't limited to physical in-house servers.

#How did you interpret the user’s needs and implement them into your software design? Why is it so important to consider the user’s needs when designing?

  The user is the one who has to use the program after it is created. If the user is dissatisfied or frustrated using something they will either not use it or seek something else. I also know that in business it is harder to get new customers than keep them.

#How did you approach designing software? What techniques or strategies would you use in the future to analyze and design a similar software application?

  At first I created my own version of code and naming convention but then I realized there is no room for negotiation for this project because this is roleplaying so I tried to follow the UML design as is. I want to look into the dropwizard authentication and authorization we did in class for security purposes. We touched on it breifly but it was one of the most interesting parts to me.

#CS-255

#Briefly summarize the DriverPass project. Who was the client? What type of system did they want you to design?

  The client was the owner of DriverPass, Liam, with his IT officer sitting in with him, Ian.

  The DriverPass project is best summarized from a document I created:
  
  The system, when completed, should allow students to create accounts on a website, over the phone, or in person to access online classes, practice tests, and schedule on-the-road training. There are 10 cars/drivers and three packages to pick from and assign to a student when they schedule on-the-road training. The main page, or dashboard, for the students should show the progress of their online tests and on-the-road training as well as their personal information and notes the drivers leave for them. There should be a way for the students to contact and be contacted by the DriverPass team. There should be access to the online classes and practice tests on the main page as well.
  The owner also wants to be able to export server logs and data to Excel for reports as well as be connected to     the DMV for new rules, policies, or sample questions. 
  DriverPass requires the system to be web-based and cloud-hosted to ensure accessibility, automated server backups, and managed security..

  
#What did you do particularly well?

  I believe I went over functional and non-functional requirements in detail without bringing up product names or being vague.
  
#If you could choose one part of your work on these documents to revise, what would you pick? How would you improve it?

  I would like to redo some of the beginning diagrams I created, I know how to condense the information better and make it more readable.
  
#How did you interpret the user’s needs and implement them into your system design? Why is it so important to consider the user’s needs when designing?

  It is important to consider the user's needs when designing because nobody has ideas 100% completed while thinking of every angle (e.g., what different users see, how easy it is to use). With the DriverPass project it focused on the students, driver, and the owner. There were mentions of the secretary and the IT groups but most of the details focused on the three prior groups (e.g., provided with a web page design for customers/students). 
  
#How do you approach designing software? What techniques or strategies would you use in the future to analyze and design a system?

  There are a few strategies you use when designing software (courtesy of https://www.geeksforgeeks.org/system-design/software-engineering-system-design-strategy/):

  1. Top-Down Design: This strategy starts with a high-level view of the system and gradually breaks it down into       smaller, more manageable components.
  
  2. Bottom-Up Design: This strategy starts with individual components and builds the system up, piece by piece.
  
  3. Iterative Design: This strategy involves designing and implementing the system in stages, with each stage building on the results of the previous stage.
  
  4. Incremental Design: This strategy involves designing and implementing a small part of the system at a time, adding more functionality with each iteration.
    
  5. Agile Design: This strategy involves a flexible, iterative approach to design, where requirements and design evolve through collaboration between self-organizing and cross-functional teams.

  I would go for a hybrid model combining Top-Down and Agile Bottom-Up hybrid or Iterative Bottom-Up hybrid, depending on whether the client wants to offer feedback often. I would use the top-down strategy to create and plan the system and then break it down into smaller components. Then, switch to the agile or iterative appoach building up. 
  The requirement gathering and system modeling, diagrams, are superior analyzing techniques I understood and liked using. Using top-down to plan a system and then starting from the smaller components build up is what I'd do. One of my favorite parts is viewing the system from different user's perspective and how the interface and pathing will look to them as they navigate the system.
