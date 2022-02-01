for $e in //employee
for $o in distinct-values(//officeCode)
group by $id := $o
order by $id
return 
<ol id ="{$id}">{  
  for $em in distinct-values(//employee[officeCode = $id])
  return  <li>{$em}</li> 
}</ol>



(:
Arquivo Xpath - executar com o basex
:)




(:

for $e in //employee
for $o in distinct-values(//officeCode)
group by $id := $o
order by $id
return 
<ol id ="{$id}">{  
  for $em in distinct-values(//employee[officeCode = $id]/lastName/text()) 
  return  <li>{$em}</li> 
}</ol>

:)



(:

TRÁS APENAS PRIMEIRO NOME (Versão com erro: apenas um <li> para todos empregados)

let $e :=(//employee/lastName)
for $o in distinct-values(//officeCode)
let $q:=(//employee[officeCode = $o]/lastName/text())
  order by $o
  group by $o
return
   <ol id="{$o}">      
        <li>{$q}</li>
   </ol>
:)


(:

FUNCIONANDO - ID FORA DE ORDEM E APENAS PRIMEIRO NOME

for $e in //employee

group by $id := distinct-values(//officeCode)
order by $id
return 
<ol id ="{$id}">{  
  for $em in distinct-values(//employee[officeCode = $id]/lastName/text()) 
  return  <li>{$em}</li> 
}</ol>

:)


(:
let $e :=(//employee/lastName)
for $o in distinct-values(//officeCode)
let $q:=(//employee[officeCode = $o])
  order by $o
  group by $o
return
   <ol id="{$o}">      
        <li>{$q}</li>
   </ol>
:)



(:
for $e in distinct-values(//employee/lastName)

let $first_letter := substring ($e,1,1)
(:for $o in distinct-values(//officeCode):)

  order by $first_letter
  group by $first_letter

return
   <group quantidade="{count($e)}">
      {for $person in $e
        return<li>{$person}</li>
      }</group>
:)



(:

  let $e :=(//employee/lastName)


for $o in distinct-values(//officeCode)
let $q:=count(//employee[officeCode = $o])

  order by $o
  group by $o

return
   <ol id="{$o}">
      
        <li>{$q}</li>
      </ol>


:)


(:

<ol>{
let $officecodes :=
for $j in distinct-values(//officeCode)
let $q:=count(//employee[officeCode = $j])
return <li>{$j} ({$q})</li>
return $officecodes
}</ol>

:)