# cs3560-hw2-mini-twitter
instructions: https://docs.google.com/document/d/19TEKw_z7PvJkdvvqwxwm_yabbtoek9nQgL0G4BQKjiU/edit?tab=t.0


project 3 upgrades:

1. fixed the post counting issue (now only counts post on creation, preventing duplicate counting)
2. latest post will appear at the top of a user's feed
3. user ids are not automatically assigned anymore
4. duplicate user ids (between both users and groups) are prohibited
5. posts shows creation time before text (will show date instead if post is created on a different day)
6. made a new class for id validation
X. cosmetic ui changes


project 3 instruction implementation notes:
1. included id duplication protection (would make following other users more complicated if i hadn't)
2. user and their current group's creation time is viewed in console upon opening user view
3. "last updated" admin button shows the userid of the person who last made a post (feed update originator)