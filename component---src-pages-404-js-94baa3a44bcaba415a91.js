(window.webpackJsonp=window.webpackJsonp||[]).push([[6],{171:function(e,t,a){"use strict";a.r(t);var n=a(0),r=a.n(n),i=a(185);t.default=function(){return r.a.createElement(i.a,null,r.a.createElement("h1",null,"NOT FOUND"),r.a.createElement("p",null,"You just hit a route that doesn't exist... the sadness."))}},175:function(e,t,a){"use strict";a.d(t,"b",function(){return s});var n=a(0),r=a.n(n),i=a(4),l=a.n(i),o=a(33),c=a.n(o);a.d(t,"a",function(){return c.a}),a.d(t,"c",function(){return o.withPrefix});a(176);var A=r.a.createContext({}),s=function(e){return r.a.createElement(A.Consumer,null,function(t){return e.data||t[e.query]&&t[e.query].data?(e.render||e.children)(e.data?e.data.data:t[e.query].data):r.a.createElement("div",null,"Loading (StaticQuery)")})};s.propTypes={data:l.a.object,query:l.a.string.isRequired,render:l.a.func,children:l.a.func}},176:function(e,t,a){var n;e.exports=(n=a(181))&&n.default||n},177:function(e,t,a){"use strict";a(94);var n=a(0),r=a.n(n),i=a(17),l=a(175),o=a(93),c=o.StyleSheet.create({active:{fontWeight:"bold"}}),A=function(e){var t=e.location,a=e.to,n=e.children,i=e.exact,A=t.pathname.split("/").filter(function(e){return e}),s=Object(l.c)(a).split("/").filter(function(e){return e}),m=[];if(!1===i){if(s.length<=A.length){for(var g=!0,u=0;u<s.length;u++)A[u]!==s[u]&&(g=!1);g&&m.push(c.active)}}else if(A.length===s.length){for(var E=!0,d=0;d<A.length;d++)A[d]!==s[d]&&(E=!1);E&&m.push(c.active)}return r.a.createElement(l.a,{to:a,className:Object(o.css)(m)},n)};t.a=function(e){return r.a.createElement(i.Location,null,function(t){return r.a.createElement(A,{location:t.location,to:e.to,exact:e.exact},e.children)})}},180:function(e){e.exports={data:{site:{siteMetadata:{title:"Workflow"}}}}},181:function(e,t,a){"use strict";a.r(t);a(34);var n=a(0),r=a.n(n),i=a(4),l=a.n(i),o=a(74),c=a(2),A=function(e){var t=e.location,a=c.default.getResourcesForPathnameSync(t.pathname);return r.a.createElement(o.a,Object.assign({location:t,pageResources:a},a.json))};A.propTypes={location:l.a.shape({pathname:l.a.string.isRequired}).isRequired},t.default=A},182:function(e,t){e.exports="data:image/png;base64,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"},185:function(e,t,a){"use strict";var n=a(180),r=a(0),i=a.n(r),l=a(4),o=a.n(l),c=a(187),A=a.n(c),s=a(175),m=a(93),g=a(182),u=a.n(g),E=m.StyleSheet.create({outerContainer:{backgroundColor:"#1b2126",marginBottom:"0",position:"sticky",top:"0",color:"eceef1",marginTop:"96pt"},innerContainer:{margin:"0 auto",maxWidth:1400,padding:"1.45rem 1.0875rem"},columnGrid:{display:"grid",gridTemplateColumns:"repeat(3, 1fr);",gridColumnGap:"24px",paddingTop:"24pt",paddingBottom:"48pt"},logo:{width:"32pt",height:"32pt"},contributorList:{listStyle:"none",margin:"0",padding:"0"}}),d=function(e){e.siteTitle;return i.a.createElement("div",{className:Object(m.css)(E.outerContainer)},i.a.createElement("div",{className:Object(m.css)(E.innerContainer)},i.a.createElement("div",{className:Object(m.css)(E.columnGrid)},i.a.createElement("div",null,i.a.createElement("img",{src:u.a,alt:"logo",className:Object(m.css)(E.logo)})),i.a.createElement("div",null,i.a.createElement("ul",{className:Object(m.css)(E.contributorList)},i.a.createElement("li",null,"© 2019 Square, Inc."))),i.a.createElement("div",null,i.a.createElement("ul",{className:Object(m.css)(E.contributorList)},i.a.createElement("li",null,"David Apgar"),i.a.createElement("li",null,"Tim Donnelly"),i.a.createElement("li",null,"Zach Klippenstein"),i.a.createElement("li",null,"Ray Ryan"))))))},C=(a(183),a(177)),B=m.StyleSheet.create({outerContainer:{backgroundColor:"#FFFFFF",marginBottom:"1.45rem",position:"sticky",top:"0"},innerContainer:{margin:"0 auto",maxWidth:1400,padding:"1.45rem 1.0875rem",display:"flex",flexDirection:"row",alignItems:"center"},title:{margin:0,display:"block",fontWeight:"medium",fontSize:"1.2em",letterSpacing:"0.05em"},link:{textDecoration:"none",color:"#3e4348"},sectionLink:{display:"block",marginLeft:"36px"}}),p=function(e){var t=e.siteTitle;return i.a.createElement("div",{className:Object(m.css)(B.outerContainer)},i.a.createElement("div",{className:Object(m.css)(B.innerContainer)},i.a.createElement("h1",{className:Object(m.css)(B.title)},i.a.createElement(s.a,{to:"/",className:Object(m.css)(B.link)},t)),i.a.createElement("span",{className:Object(m.css)(B.sectionLink)},i.a.createElement(C.a,{to:"/documentation",exact:!1},"Documentation"))))},I=m.StyleSheet.create({outerContainer:{marginBottom:"1.45rem"},innerContainer:{margin:"0 auto",maxWidth:1400,padding:"1.45rem 1.0875rem"}}),h=function(e){return i.a.createElement("div",{className:Object(m.css)(I.outerContainer)},i.a.createElement("div",{className:Object(m.css)(I.innerContainer)},e.children))},Q=m.StyleSheet.create({html:{margin:"0px",padding:"0px"},body:{fontFamily:["Square Market","Helvetica","Arial","sans-serif"],color:"#3e4348",lineHeight:"1.6em",margin:"0px",padding:"0px"},mainColumn:{display:"flex",flexDirection:"column",minHeight:"100vh",alignItems:"stretch"},growContainer:{flexGrow:"1"}}),S=function(e){var t=e.children;return i.a.createElement(s.b,{query:"755544856",render:function(e){return i.a.createElement(i.a.Fragment,null,i.a.createElement(A.a,{title:e.site.siteMetadata.title,meta:[{name:"description",content:"A reactive application architecture for Swift and Kotlin"},{name:"keywords",content:"workflow, kotlin, swift, reactive"}]},i.a.createElement("html",{lang:"en",className:Object(m.css)(Q.html)}),i.a.createElement("body",{className:Object(m.css)(Q.body)}),i.a.createElement("link",{rel:"stylesheet",href:"https://d1g145x70srn7h.cloudfront.net/fonts/sqmarket/sq-market.css"}),i.a.createElement("style",{type:"text/css"},"\n            a {\n              color: #49a4d5;\n              text-decoration: none;\n            }\n            a:visited {\n              color: #5174d1;\n            }\n            a:active, a:hover {\n              text-decoration: underline;\n            }\n          ")),i.a.createElement("div",{className:Object(m.css)(Q.mainColumn)},i.a.createElement(p,{siteTitle:e.site.siteMetadata.title}),i.a.createElement("div",{className:Object(m.css)(Q.growContainer)},i.a.createElement(h,null,t)),i.a.createElement(d,null)))},data:n})};S.propTypes={children:o.a.node.isRequired};t.a=S}}]);
//# sourceMappingURL=component---src-pages-404-js-94baa3a44bcaba415a91.js.map