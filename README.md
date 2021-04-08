# Scala-Spark-Streaming- 
Business Use Case Definition:

Telecom Company X is aiming to increase the data usage of its customer base as a key objective for 2021.
So X decided to target their data customer base with specialized campaigns to upsell their data packages.
X also partnered up with various famous applications to give the customers extra promotions to increase the traffic on the applications.
X is looking for aspiring data engineers to transform such strategic decision into a reality. Aspiring data engineers will work with streaming data capturing the data usage traffic for X customers.
Since the customer base generates ridiculous amount of traffic, X is only interested in a subset of the base “Segment” to try the new initiative on.
To insure a good customer experience, X wants to target the segment with only one campaign a day per application.

Data Definition:
DATA_yyyyMMddHHmm.csv
Contains a data record per line indicating one session. Field are ordered as the following:
Msisdn: customer dial.
Service Identifier: Id for the service/application
Traffic Volume: amount of download traffic used in the session
Timestamp : timestamp of the transaction
SEGMENT.csv
Contains list of dials X Company interested to track their usage
RULES.csv
Contains the rules which defines the rules for sending a notification per service identifier/application. Fields are ordered as the following:
Service Identifier: Id for the service/application
Service Description : Service/application name
Start time : the lower bound of usage tracking window
End time : the upper bound of usage tracking window
Total volume : the volume amount that needs to be exceeded to mark a valid rule to send the notification


Data Generation 
(Create directory paths before running the commands):
Generate the rules 

Generate the segment

Generate the streaming data





Deliverables:
One fat jar which takes multiple parameters in this following order
Data directory path
Segment data directory path
Rules data directory path
Output directory path – One directory per application ( Contains the records which passed rules criteria successfully )
The program needs to have the following characteristics:
Fault tolerant (input errors shouldn’t break down your software)
Smooth Recovery (start processing again from the last checkpoint)
You are eligible to use your favorite framework/s to achieve the task.


Sample input & Output:
Data
0124637855,5,367,20200417193743
0127368267,7,421,20200417193743
0126545098,1,315,20200417193743
0123979727,5,16,20200417193743
0127074387,6,111,20200417193743
0126545098,1,372,20200417193744
0126545098,2,222,20200417193744
Segment
0123917827
0126545098
0128419091
Rules
1,Twitter,8,20,147
2,WhatsApp,9,18,68
3,Youtube,10,20,99
Output
0126545098,1,315,20200417193743
0126545098,2,222,20200417193744
