(:JIQS: ShouldRun; Output="(a*cada*, *, *c*bra, brcdbr, abbraccaddabbra, b, AAAA, bbbb, carted, )" :)
replace("abracadabra", "bra", "*"),
replace("abracadabra", "a.*a", "*"),
replace("abracadabra", "a.*?a", "*"),
replace("abracadabra", "a", ""),
replace("abracadabra", "a(.)", "a$1$1"),
replace("AAAA", "A+", "b"),
replace("AAAA", "B+", "b"),
replace("AAAA", "A+?", "b"),
replace("darted", "^(.*?)d(.*)$", "$1c$2"),
replace("created", "created", "")

(: general tests :)