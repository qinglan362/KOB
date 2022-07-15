import {GameObject} from "@/assets/scrpts/GameObject";
import {Cell} from "@/assets/scrpts/Cell";
export class Snake extends GameObject{
    constructor(info,gamemap) {
        super();
        this.id=info.id;
        this.color=info.color;
        this.gamemap=gamemap;
        this.cells=[new Cell(info.r,info.c)];
        this.speed=4;
        this.direction=-1;//0 1 2 3 上右下左
        this.status="idle";//静止,move动 die 死亡
        this.next_cell=null;
        this.dr=[-1,0,1,0];
        this.dc=[0,1,0,-1];
        this.step=0;
    }
    start() {
    }
    set_direction(d){
         this.direction=d;
    }
    next_step(){
        const d=this.direction;
        this.next_cell=new Cell(this.cells[0].r+this.dr[d],this.cells[0].c+this.dc[d]);
         this.direction=-1;
         this.status="move";
         this.step++;
         const  k=this.cells.length;
         for (let i=k;i>0;i--)
             this.cells[i]=JSON.parse(JSON.stringify(this.cells[i-1]));
    }
    update_move(){

    }
    update() {
        if(this.status==='move')
            this.update_move();
        this.render();
    }
    render(){
        const L=this.gamemap.L;
        const ctx=this.gamemap.ctx;
        ctx.fillStyle=this.color;
        for(const  cell of this.cells){
            ctx.beginPath();
            ctx.arc(cell.x*L,cell.y*L,L/2,0,Math.PI*2);
            ctx.fill();
        }
    }
}