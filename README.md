# anf-code-challenge

Steps to install: 

1. Run mvn clean install -PautoInstallSinglePackage at root level. 
2. Install content packages from Example 1, 2 and 3 folders.
3. Navigate to http://localhost:4502/sites.html/content/anf-code-challenge/us/en
4. All exercise contents pages are created using the respective exercise name



Notes: 

1. Self contained installables, no dependencies other than example packages pre-provided with the originals.
2. Service User for the project and all its required privileges are controlled through RepoInit scripts
3. CSRF Filter OSGI modified to alllow POST call on author environment. 
4. JUnit test cases are not written for Exercise 2. Majorily its been a while and am a bit out of touch. We generate JUnit test cases with the help of DiffBlue or Devmate. 

