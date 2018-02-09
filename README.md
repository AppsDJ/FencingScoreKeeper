## FENCING MATCH SCOREKEEPER

A scorekeeper app for a slightly simplified fencing match.



### Fencing match rules in brief:

- Points are scored through the touch with the lets-just-call-it ’sword’ on the accepted areas of the body of the opponent - ares dependent on the type of ’sword’ used in the match.
- To indicate which fencer scored, the light on the score-keeping board on the side of the scorer turns on. For fencer on the left the light is red, the one on the right is green. (The board in the app is a not-so-bad depiction of an actual board used in competitions)
- Penalty cards are issued when a fault is committed by a fencer. What the faults are is beyond the scope of our little introduction of the app. For score keeping two yellow cards received means a red card. A red card means a point is deducted off the score of the fencer to whom the card was issued. A black card which disqualifies the fencer is issued for things like showing disrespect to opponent or referee - for simplification the app is not using a black card.
- In terms of match length ( simplified version here) : the bout must stop after three minutes of fencing.

### Screenshots

![alt text](https://github.com/AppsDJ/FencingScoreKeeper/blob/master/fencing_score_keeper_prortrait_3.png)
![alt text](https://github.com/AppsDJ/FencingScoreKeeper/blob/master/fencing_score_keeper_land_2.png)




### Features of the app:
- At load only the ‘Start’ button is active, all others are deactivated, countdown timer is set at 03:00.
- At press ‘Start’ button the countdown timer is started and all other buttons are activated. ’Start’ and “Resume’ are deactivated.
- At press ‘Pause’ timer is stopped and all buttons are deactivated except ‘Resume’ and ‘Reset’.
While match score keeping  is active:
- At press “Touché!’ a point is assigned to the fencer who scored a touch. Also the green or red light respectively are turned depending on which fencer scored.
- At press ‘Yellow card’ the card is recorded in the ‘Penalty Cards’ score keeper. At two ‘Yellow Card’ presses a red card is recorded and one point is deducted off the main score of fencer at fault.
- At press ‘Red card’ the card is recorded in the ‘Penalty Cards’ score keeper. One point is deducted off the score of fencer at fault.
- At Countdown timer reaching 00:00 all buttons are deactivated except ‘Reset’. Scores are still being displayed.
- At press ‘Reset’ timer is reset to 03:00, scores cleared to 0 and all buttons deactivated except ‘Start’.
- On screen rotation all states (counter, scores, buttons activation states) are saved and restored seamlessly in new screen orientation.
