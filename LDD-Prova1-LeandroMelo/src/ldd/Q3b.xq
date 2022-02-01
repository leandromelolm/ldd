<ul>{
let $jobtitles :=
for $j in distinct-values(//jobTitle)
let $q:=count(//employee[jobTitle = $j])
return <li>{$j} ({$q})</li>
return $jobtitles
}</ul>