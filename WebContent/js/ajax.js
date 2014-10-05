//Variables qui empechent le spam de service ( duplication des post ect)
var boolPost = true;
var boolSupPost =true;
var boolSupCom =true;
var boolCom = true;
var boolAddF = true;
var boolAccF = true;
var boolSupF = true;
var boolDeco = true;
var varshow = false;
var boolInscr = true;
var boolCo = true;
var listfriend ; //Stoque la liste des amis pour les différents affichages
var varshow = false;
var listfriendAtt ; //Liste des amis en attente d'ajout'
var $_GET = {}; //Variable passer dans le GET
    

/************Inscription**********/

function inscription(id,pass,email,nom,prenom){
    if(boolInscr){
        boolInscr = false;
        
        $.ajax ({
            type : "POST" ,
            url: "newuser",
            data:"login="+id+"&pass="+pass+"&fname="+prenom+"&name="+nom+"&mail="+email, 
            dataType : "json" ,
            success: function(rep){
                if(!(typeof (rep.code) == 'undefined')){
                    boolInscr = true;
                    alert("Erreur "+rep.code+" : "+rep.mess);
                }else{
                    boolInscr = true;
                    pop('inscrip');
                } 
            },
            error :function(jqXHR, textStatus , errorThrown ){
                boolInscr = true;
                alert("textStatus");
            }
        })
    }
}
    
    
/***********Login***********/
    
function login_ajax(login,mdp){
    if(boolCo){
        boolCo = false;
        
        $.ajax ({
            type : "POST" ,
            url: "login",
            data:"login="+login+"&pass="+mdp, 
            dataType : "json" ,
            success: function(rep){
                if(!(typeof (rep.code) == 'undefined')){
                    boolCo = true;
                    erreurServlet(rep.code,rep.mess);
                }else{
                    createCookie('id_u',rep.id,'3');
                    createCookie('login',rep.login,'3');
                    createCookie('key_u',rep.key,'3');
                    boolCo = true;
                    window.location = "flux.jsp"
                } 
            },
            error :function(jqXHR, textStatus , errorThrown ){
                alert("textStatus");
            }
        })
    }
        
}
    
    
/***********Recupere la liste des amis***********/
    
function listF(){
    $.ajax ({
        type : "POST" ,
        url: "listfriend",
        data:"key="+readCookie("key_u")+"&idU="+readCookie("id_u"), 
        dataType : "json" ,
        success: function(rep){
            if(!(typeof (rep.code) == 'undefined')){
                erreurServlet(rep.code,rep.mess);
            }else{
                listfriend = rep;
                affPost();
            } 
        },
        error :function(jqXHR, textStatus , errorThrown ){
                
        }
    })
}
    
    
    
/**********Opération sur amis************/
    
function suppFriend(idf){
    if(boolSupF){
        boolSupF = false;
        $.ajax ({
            type : "POST" ,
            url: "removefriend",
            data:"key="+readCookie('key_u')+"&idF="+idf, 
            dataType : "json" ,
            success: function(rep){
                if(!(typeof (rep.code) == 'undefined')){
                    boolSupF = true;
                    erreurServlet(rep.code,rep.mess);
                }else{
                    boolSupF = true;
                    location.reload();
                } 
            },
            error :function(jqXHR, textStatus , errorThrown ){
                alert("textStatus");
            }
        })
    }
        
}

function accFriend(idf,rep){
    if(boolAccF){
        boolAccF = false;
        
        $.ajax ({
            type : "POST" ,
            url: "accfriend",
            data:"key="+readCookie('key_u')+"&idF="+idf+"&accF="+rep, 
            dataType : "json" ,
            success: function(rep){
                if(!(typeof (rep.code) == 'undefined')){
                    boolAccF = true;
                    erreurServlet(rep.code,rep.mess);
                }else{
                    boolAccF = true;
                    friendAtt();
                    $('#fade , .popup_block').fadeOut(function() {
                        $('#fade, a.close').remove();  //...ils disparaissent ensemble
                    });
                } 
            },
            error :function(jqXHR, textStatus , errorThrown ){
                alert("textStatus");
            }
        })
    }
        
}

 
function addFriendT(){
    if(boolAddF){
        boolAddF = false;
        $.ajax ({
            type : "POST" ,
            url: "addfriend",
            data:"key="+readCookie('key_u')+"&idF="+$_GET['id'], 
            dataType : "json" ,
            success: function(rep){
                if(!(typeof (rep.code) == 'undefined')){
                    boolAddF = true;
                    erreurServlet(rep.code,rep.mess);
                }else{
                    boolAddF = true;
                    affPost();
                    pop('friend');
                } 
            },
            error :function(jqXHR, textStatus , errorThrown ){
                alert("textStatus");
            }
        })
    }
        
}
    
    
function addFriend(idf){
    if(boolAddF){
        boolAddF = false;
        $.ajax ({
            type : "POST" ,
            url: "addfriend",
            data:"key="+readCookie('key_u')+"&idF="+idf, 
            dataType : "json" ,
            success: function(rep){
                if(!(typeof (rep.code) == 'undefined')){
                    boolAddF = true;
                    erreurServlet(rep.code,rep.mess);
                }else{
                    boolAddF = true;
                    affPost();
                    pop('friend');
                } 
            },
            error :function(jqXHR, textStatus , errorThrown ){
                alert("textStatus");
            }
        })
    }
        
}
    
    
    
/**********Opérations Commentaire************/
    
    
    
function jaimeC(idp,idc){
    $.ajax ({
        type : "POST" ,
        url: "clike",
        data:"key="+readCookie('key_u')+"&idP="+idp+"&idC="+idc+"&arg=true", 
        dataType : "json" ,
        success: function(rep){
            if(!(typeof (rep.code) == 'undefined')){
                erreurServlet(rep.code,rep.mess);
            }else{
                affPost();
            } 
        },
        error :function(jqXHR, textStatus , errorThrown ){
            alert("textStatus");
        }
    })
        
}
    
function jaimePasC(idp,idc){
    $.ajax ({
        type : "POST" ,
        url: "clike",
        data:"key="+readCookie('key_u')+"&idP="+idp+"&idC="+idc+"&arg=false", 
        dataType : "json" ,
        success: function(rep){
            if(!(typeof (rep.code) == 'undefined')){
                erreurServlet(rep.code,rep.mess);
            }else{
                affPost();
            } 
        },
        error :function(jqXHR, textStatus , errorThrown ){
            alert("textStatus");
        }
    })
        
}
    
    
    
function envoisCom(id){
    var text = document.getElementsByName("com"+id)[0].value;
    text = text.replace(/[\s]*$/g,'');
    if(boolCom){
        boolCom = false;
    
        $.ajax ({
            type : "POST" ,
            url: "addcomment",
            data:"key="+readCookie('key_u')+"&idP="+id+"&text="+text, 
            dataType : "json" ,
            success: function(rep){
                if(!(typeof (rep.code) == 'undefined')){
                    boolCom = true;
                    erreurServlet(rep.code,rep.mess);
                }else{
                    boolCom = true;
                    affPost();
                } 
            },
            error :function(jqXHR, textStatus , errorThrown ){
                alert("textStatus");
            }
        })
    }
        
}
    
function hideShowComP(id_p,id_c){
    if(boolSupCom){
        boolSupCom = false;
        
        $.ajax ({
            type : "POST" ,
            url: "supcom",
            data:"key="+readCookie("key_u")+"&idP="+id_p+"&idC="+id_c, 
            dataType : "json" ,
            success: function(rep){
                if(!(typeof (rep.code) == 'undefined')){
                    boolSupCom = true;
                    erreurServlet(rep.code,rep.mess);
                }else{
                    boolSupCom = true;
                    $("."+id_c).slideToggle('slow');
                    $("."+id_p).find(".button2").find("span")[0].firstChild.data = -1+parseInt($("."+id_p).find(".button2").find("span")[0].firstChild.data);
                } 
            },
            error :function(jqXHR, textStatus , errorThrown ){
                alert("textStatus");
            }
        })
    }
}

    
    
/**********Operation Post************/
    
    
        
    
function hideShowPost(id_p){
    if(boolSupPost){
        boolSupPost = false;
        
        $.ajax ({
            type : "POST" ,
            url: "suppost",
            data:"key="+readCookie("key_u")+"&idP="+id_p, 
            dataType : "json" ,
            success: function(rep){
                if(!(typeof (rep.code) == 'undefined')){
                    boolSupPost = true;
                    erreurServlet(rep.code,rep.mess);
                }else{
                    boolSupPost = true;
                    $("."+id_p).slideToggle('slow');
                } 
            },
            error :function(jqXHR, textStatus , errorThrown ){
                alert("textStatus");
            }
        })
    }
}


    
function post(){
    var text = document.getElementsByName("messageA")[0].value;
    text = text.replace(/[\s]*$/g,'');  
    if(boolPost){
        boolPost= false;
        $.ajax ({
            type : "POST" ,
            url: "addpost",
            data:"key="+readCookie('key_u')+"&idA="+readCookie('id_u')+"&idH="+readCookie('id_u')+"&text="+text, 
            dataType : "json" ,
            success: function(rep){
                if(!(typeof (rep.code) == 'undefined')){
                    erreurServlet(rep.code,rep.mess);
                    boolPost=true;
                }else{
                    boolPost=true;
                    varshow = true;
                    document.getElementsByName("messageA")[0].value="";
                    affPost();
                } 
            },
            error :function(jqXHR, textStatus , errorThrown ){
                alert("textStatus");
            }
        })
    }
}
    
     
function rePost(idH,id){
    if(boolPost){
        boolPost= false;
        $.ajax ({
            type : "POST" ,
            url: "addpost",
            data:"key="+readCookie('key_u')+"&idA="+readCookie('id_u')+"&idH="+idH+"&text="+document.getElementsByName(id+"_text")[0].firstChild.data, 
            dataType : "json" ,
            success: function(rep){
                if(!(typeof (rep.code) == 'undefined')){
                    boolPost=true;
                    erreurServlet(rep.code,rep.mess);
                }else{
                    boolPost=true;
                    varshow = true;
                    affPost();
                } 
            },
            error :function(jqXHR, textStatus , errorThrown ){
                alert("textStatus");
            }
        })
    }
}
    
function jaime(id){
    $.ajax ({
        type : "POST" ,
        url: "plike",
        data:"key="+readCookie('key_u')+"&idP="+id+"&arg=true", 
        dataType : "json" ,
        success: function(rep){
            if(!(typeof (rep.code) == 'undefined')){
                erreurServlet(rep.code,rep.mess);
            }else{
                affPost();
            } 
        },
        error :function(jqXHR, textStatus , errorThrown ){
            alert("textStatus");
        }
    })
        
}
    
function jaimePas(id){
    $.ajax ({
        type : "POST" ,
        url: "plike",
        data:"key="+readCookie('key_u')+"&idP="+id+"&arg=false", 
        dataType : "json" ,
        success: function(rep){
            if(!(typeof (rep.code) == 'undefined')){
                erreurServlet(rep.code,rep.mess);
            }else{
                affPost();
            } 
        },
        error :function(jqXHR, textStatus , errorThrown ){
            alert("textStatus");
        }
    })
        
}
    
    
    
/**********Deconnexion************/
    
function suppCook(key){
    if(boolDeco){
        boolDeco = false;
        
        $.ajax ({
            type : "POST" ,
            url: "logout",
            data:"key="+key, 
            dataType : "json" ,
            success: function(rep){
                eraseCookie("id_u");
                eraseCookie('key_u');
                eraseCookie('login');
                boolDeco = true;
                window.location = "index.jsp";
            },
            error :function(jqXHR, textStatus , errorThrown ){
                boolDeco = true;
                alert("textStatus");
            }
        })
    }
}
    
    
    
/***********List amis attente***********/
    
function friendAtt(){
    $.ajax ({
        type : "POST" ,
        url: "listfriend",
        data:"key="+readCookie("key_u"), 
        dataType : "json" ,
        success: function(rep){
            if(!(typeof (rep.code) == 'undefined')){
                erreurServlet(rep.code,rep.mess);
            }else{
                listfriendAtt = rep;
                afficheAttF();
            } 
        },
        error :function(jqXHR, textStatus , errorThrown ){
                
        }
    })
}
    
    
    
    /**********************/
    
    
    
    /**********************/
    
    
    
    /**********************/
    
    
    
    /**********************/
    
    
    
    /**********************/
    
    
    /**********************/