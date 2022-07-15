const AcGameObject=[];
export class GameObject{
    constructor() {
        AcGameObject.push(this);
        this.timedelta=0;
        this.has_called_start=false;
    }
    start(){

    }
    update(){

    }

    on_destroy(){

    }

    destroy(){
        this.on_destroy();
        for(let i in AcGameObject){
            const obj=AcGameObject[i];
            if(obj===this){
                AcGameObject.splice(i);
                break;
            }
        }
    }
}

let last_timestamp;
const step=timestamp=>{
    for(let obj of AcGameObject){
        if(!obj.has_called_start){
            obj.has_called_start=true;
            obj.start();
        }else{
            obj.timedelta=timestamp-last_timestamp;
            obj.update();
        }
    }
    last_timestamp=timestamp;
    requestAnimationFrame(step)
}

requestAnimationFrame(step)