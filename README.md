# LogifutureWallet


*Software Developer Coding Challenge![ref1]![ref1]*

Congratulations on getting to this stage and thank you for taking the time to solve the following exercise.

The assignment should take less than 5-6 hours of your time, and you can submit a partial or incomplete solution.

Here are some ground rules:

- We would like to see the solution implemented in **Java**
- You can **use any library and/or framework** that you see fit
- You **don’t** need to deal with deployment, CI/CD, etc.
- Make sure to **provide instructions** on how to run your deliverable and any dependencies it might have.
- Write code as you would expect if you were working as part of a team and deploying to production


Should you need any further information, please do not hesitate to contact us. **Best of luck!**

**1 Wallet System**

In a sports betting platform there needs to be a place where we can store the funds for each user. We call this a *wallet*.

Funds in a wallet can increase (e.g. by winning a bet, depositing money, getting a bonus etc.) or decrease (e.g. by placing a bet, withdrawing money etc.).

We would like you to design and implement a service which is in charge to store the funds and provide the functionality for manipulating the balance. The system should be able to handle a large number of concurrent users and should be fault-tolerant, meaning that it should continue to operate even if some of the nodes in the system fail.

Requirements:

- We need a way to create a wallet.
- We need a way to add funds to a wallet.
- We need a way to remove funds from a wallet.
- We need a way to query the current state of a wallet.
- The client should interact with service with REST APIs.

Try to keep the code simple and design/write the code as you would expect if you were deploying to prod.

Feel free to add any extra features or designs to the wallet if you deem it necessary.
