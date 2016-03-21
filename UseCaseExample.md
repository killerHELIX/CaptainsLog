# Use Case Example Document

### Template

**Use Case Title**  
**Description**: Brief sentence or two describing intend purpose and result
of use case.

**Basic Path**:
  * 1. Action for step 1.
  * 2. Action for step 2.
  * ...
  * n-1. Action for step n-1.
  * n. Action for step n.

**Alternate Path**:
  * x. Alternate path begins at Basic Path step x.
    * xa. Action for alternate path step a.
    * xb. Action for alternate path step b.
    * ...
    * Final step terminates use case or rejoins basic path at step y.

### Example (Taken from 430 project)
**Print Graphical Plot to Screen**
**Description: ** User can elect to print a graphical plot of specific data
points to the screen.

**Basic Path**
  * 1. The user can select the 'Plot Data' option.
  * 2. The user can select which data to plot.
  * 3. The user can select a range of time from which data will be pulled.
  * 4. The user can select one or more types of calculations to run on the dataset.
  * 5. System displays plot to screen.
  * END.

**Alternate Paths**
  * 5. User does not select calculations.
    * 5a. Raw data points will be plotted against time.
    * END.
