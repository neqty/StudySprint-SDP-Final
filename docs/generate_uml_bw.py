import matplotlib
matplotlib.use('Agg')
import matplotlib.pyplot as plt
from matplotlib.patches import Rectangle, FancyArrowPatch
import os
from PIL import Image, ImageOps, ImageEnhance

ROOT='/mnt/data/StudySprint_Final'
OUT=ROOT+'/images'
DOCS=ROOT+'/docs'
os.makedirs(OUT, exist_ok=True)

def clear_axes(ax):
    ax.set_xlim(0,100); ax.set_ylim(0,100); ax.axis('off')

def uml_node(ax, x,y,title,details='',w=27,h=31,abstract=False):
    # x,y are centers
    rect = Rectangle((x-w/2,y-h/2),w,h,facecolor='white',edgecolor='black',linewidth=0.95,zorder=3)
    ax.add_patch(rect)
    header_h=12 if details else h
    if details:
        ax.plot([x-w/2,x+w/2],[y+h/2-header_h,y+h/2-header_h],color='black',lw=.75,zorder=4)
        ax.text(x,y+h/2-header_h/2,title,ha='center',va='center',fontsize=7.4, fontweight='bold',style='italic' if abstract else 'normal',zorder=5)
        lines=details.split('\n')
        for i,t in enumerate(lines[:2]):
            ax.text(x-w/2+1.8,y+h/2-header_h-6-7*i,t,ha='left',va='center',fontsize=6.8,zorder=5)
    else:
        ax.text(x,y,title,ha='center',va='center',fontsize=7.8,fontweight='bold',style='italic' if abstract else 'normal',zorder=5)

def connect(ax,p1,p2,kind='use',label=None):
    # endpoints explicit; simple UML-esque directional lines
    kw={'arrowstyle':'-|>','mutation_scale':10,'lw':1.0,'color':'black'}
    if kind=='inherit':
        kw.pop('color',None)
        kw.update({'arrowstyle':'-|>','mutation_scale':17,'lw':.9, 'linestyle':'dashed', 'facecolor':'white','edgecolor':'black'})
    elif kind=='dep':
        kw.update({'linestyle':'dashed'})
    arr=FancyArrowPatch(p1,p2,**kw,zorder=1)
    ax.add_patch(arr)
    if label:
        x=(p1[0]+p2[0])/2; y=(p1[1]+p2[1])/2
        ax.text(x,y+3,label,fontsize=6.5,ha='center',va='bottom',bbox=dict(facecolor='white',edgecolor='none',pad=0.4),zorder=6)

panels = [
 ('1. BUILDER  -  Erasyl',[
   (25,49,'StudyPlanBuilder','+name(String)\n+build(): StudyPlan',33,34),
   (77,49,'StudyPlan','+getTasks()\n+setStrategy()',30,34)],
  [((42,49),(62,49),'dep','builds')]),
 ('2. STRATEGY  -  Erasyl',[
   (15,50,'ScheduleService','+generate()',26,28),
   (49,50,'<<interface>>\nScheduleStrategy','+arrange(tasks)',28,35),
   (84,70,'PriorityStrategy','',27,22),
   (84,25,'ShortestStrategy','',27,22)],
  [((28,50),(35,50),'use','uses'),((70.5,70),(63,56),'inherit',None),((70.5,25),(63,43),'inherit',None)]),
 ('3. FACTORY METHOD  -  Arsen',[
   (20,57,'TaskCreator','+create()\n+makeTask()',27,34),
   (52,70,'ReadingTaskCreator','',32,22),
   (52,27,'PracticeTaskCreator','',32,22),
   (85,70,'ReadingTask','',25,22),
   (85,27,'PracticeTask','',25,22)],
  [((36,70),(32,64),'inherit',None),((37,27),(32,45),'inherit',None),((68,70),(72,70),'dep','creates'),((68,27),(72,27),'dep','creates')]),
 ('4. DECORATOR  -  Arsen',[
   (50,68,'<<interface>>\nSessionText','+describe()\n+totalMinutes()',35,32),
   (17,25,'BasicSessionText','',29,22),
   (50,25,'BreakDecorator','',29,22),
   (83,25,'TipDecorator','',29,22)],
  [((17,36),(37,58),'inherit',None),((50,36),(50,52),'inherit',None),((83,36),(63,58),'inherit',None)]),
 ('5. OBSERVER  -  Abylaikhan',[
   (18,50,'StudyFacade','+addObserver()\n+changed()',27,33),
   (51,50,'<<interface>>\nPlanObserver','+onPlanChanged()',29,33),
   (84,69,'ActivityHistory','',27,22),
   (84,26,'MainFrame','',27,22)],
  [((31.5,50),(36.5,50),'use','notifies'),((70.5,69),(65,55),'inherit',None),((70.5,26),(65,43),'use','registers')]),
 ('6. FACADE  -  Abylaikhan',[
   (17,50,'MainFrame','',26,25),
   (49,50,'StudyFacade','+addTask()\n+generateSchedule()',30,34),
   (84,70,'ScheduleService','',27,22),
   (84,50,'PlanRepository','',27,22),
   (84,21,'TaskCreators','',27,22)],
  [((30,50),(34,50),'use','calls'),((64,58),(70,70),'use',None),((64,50),(70,50),'use',None),((64,42),(70,21),'use',None)]),
 ('7. ADAPTER  -  Adilzhan',[
   (19,50,'<<interface>>\nTaskImporter','+importTasks()',29,34),
   (52,50,'LegacyCsvAdapter','+importTasks()',32,34),
   (85,50,'OldCsvReader','+readRows()',26,34)],
  [((36,50),(34,50),'inherit',None),((68,50),(72,50),'use','wraps')]),
 ('8. BRIDGE  -  Adilzhan',[
   (24,70,'StudyReport','+create()',27,26),
   (75,70,'<<interface>>\nReportFormat','+write()',30,29),
   (12,22,'SummaryReport','',22,22),
   (39,22,'DetailedReport','',22,22),
   (66,22,'PlainTextFormat','',24,22),
   (89,22,'HtmlFormat','',19,22)],
  [((37.5,70),(60,70),'use','has a'),((12,33),(21,57),'inherit',None),((39,33),(28,57),'inherit',None),((66,33),(72,55),'inherit',None),((89,33),(80,55),'inherit',None)])
]

fig,axes=plt.subplots(4,2,figsize=(16.54,11.69),dpi=210)
fig.patch.set_facecolor('white')
fig.subplots_adjust(left=.035,right=.965,top=.945,bottom=.055,hspace=.13,wspace=.075)
for ax,(heading,nodes,edges) in zip(axes.flatten(),panels):
    clear_axes(ax)
    ax.add_patch(Rectangle((.3,.3),99.4,99.4,facecolor='white',edgecolor='black',lw=.95,zorder=0))
    ax.text(4,93,heading,fontsize=10.5,fontweight='bold',va='center',ha='left')
    ax.plot([4,96],[86,86],lw=.9,color='black')
    for p1,p2,kind,label in edges: connect(ax,p1,p2,kind,label)
    for x,y,t,d,w,h in nodes: uml_node(ax,x,y,t,d,w,h,'<<interface>>' in t)
fig.suptitle('STUDY SPRINT  |  UML CLASS DIAGRAM - PATTERN PARTICIPANTS',fontweight='bold',fontsize=13,y=.986)
fig.text(.5,.019,'Solid arrow = association/use    |    Dashed open arrow = inheritance/implementation or dependency (as labeled)',ha='center',fontsize=8)
fig.savefig(OUT+'/uml_class_bw.png',facecolor='white',dpi=210)
fig.savefig(OUT+'/uml_class_bw.svg',facecolor='white')
plt.close(fig)

# One overview diagram showing integration and domain classes in a tidy academic layout
fig,ax=plt.subplots(figsize=(11.69,8.27),dpi=210)
fig.patch.set_facecolor('white'); ax.axis('off'); ax.set_xlim(0,100);ax.set_ylim(0,100)
ax.text(4,96,'STUDY SPRINT  |  SIMPLIFIED CLASS RELATIONSHIPS',fontsize=13,fontweight='bold',ha='left')
ax.plot([4,96],[92,92],color='black',lw=1)
node_list=[
(16,78,'MainFrame','+onPlanChanged()',23,13),(49,78,'StudyFacade','+addTask()\n+generateSchedule()',26,17),
(83,78,'StudyPlan','+getTasks()\n+getSessions()',23,17),
(14,48,'TaskCreators','+forType()',22,16),(40,48,'ScheduleService','+generate()\n+display()',26,17),
(68,48,'PlanRepository','+save()\n+load()',24,17),
(88,48,'PlanObserver','+onPlanChanged()',20,16),
(17,18,'StudyTask (abstract)','+getStudyTip()',28,16),
(50,18,'StudySession','taskId, position',25,16),
(81,18,'StudyReport','+create()',22,16)]
for p1,p2,typ,label in [
 ((28,78),(36,78),'use','calls'),((62,78),(71,78),'use','manages'),
 ((41,70),(21,57),'use',None),((47,70),(43,57),'use',None),((54,69),(66,57),'use',None),
 ((60,81),(80,55),'dep','notifies'),((81,69),(27,24),'use','contains 0..*'),
 ((83,69),(56,24),'use','contains 0..*'),((57,75),(76,26),'use','exports')]:
 connect(ax,p1,p2,typ,label)
for node in node_list: uml_node(ax,*node)
fig.text(.5,.045,'Overview of main classes. The complete pattern class diagrams are provided separately.',ha='center',fontsize=9)
fig.savefig(OUT+'/uml_overview_bw.png',facecolor='white',dpi=210)
fig.savefig(OUT+'/uml_overview_bw.svg',facecolor='white')
plt.close(fig)

# Sequence diagram: manual black-and-white, lifelines and labeled messages
fig,ax=plt.subplots(figsize=(16.54,11.69),dpi=210)
fig.patch.set_facecolor('white'); ax.axis('off'); ax.set_xlim(-.6,7.6);ax.set_ylim(-.5,19)
ax.set_title('STUDY SPRINT  |  UML SEQUENCE DIAGRAM - GENERATE SCHEDULE',fontsize=14,fontweight='bold',pad=22)
labels=['Student','MainFrame','StudyFacade','ScheduleService','ScheduleStrategy','PlanRepository','PlanObserver','SessionText']
xs=list(range(8))
for x,n in zip(xs,labels):
    ax.add_patch(Rectangle((x-.45,17.75),.90,.75,edgecolor='black',facecolor='white',lw=1.1))
    ax.text(x,18.12,n,ha='center',va='center',fontsize=8.8,fontweight='bold')
    ax.plot([x,x],[17.75,.65],lw=.7,color='black',linestyle=(0,(4,5)))
rows=[
(0,1,'Choose options and Generate',False),
(1,2,'setOptions("Shortest first", true, true)',False),
(2,5,'save(plans)',False),
(2,6,'onPlanChanged("Options changed")',False),
(1,2,'generateSchedule()',False),
(2,3,'generate(currentPlan)',False),
(3,4,'arrange(unfinishedTasks)',False),
(4,3,'sorted tasks',True),
(3,2,'new StudySessions',True),
(2,5,'save(plans)',False),
(2,6,'onPlanChanged("Generated schedule")',False),
(6,1,'refresh UI',False),
(1,2,'displaySchedule()',False),
(2,3,'display(currentPlan)',False),
(3,7,'describe() / totalMinutes()',False),
(7,3,'formatted schedule',True),
(3,1,'schedule and total minutes',True),
(1,0,'Show result',True),
]
for i,(a,b,label,ret) in enumerate(rows):
    y=17.1-i*.85
    p=FancyArrowPatch((xs[a],y),(xs[b],y),arrowstyle='-|>',mutation_scale=10,lw=.88,
         linestyle='dashed' if ret else 'solid',color='black')
    ax.add_patch(p)
    mid=(xs[a]+xs[b])/2
    ax.text(mid,y+.13,label,fontsize=8.3,ha='center',va='bottom',bbox=dict(boxstyle='square,pad=.08',fc='white',ec='none'))
ax.text(0,0,'Solid arrow: method call  |  Dashed arrow: return/result',fontsize=9,ha='left')
fig.savefig(OUT+'/uml_sequence_bw.png',facecolor='white',dpi=210,bbox_inches='tight',pad_inches=.25)
fig.savefig(OUT+'/uml_sequence_bw.svg',facecolor='white',bbox_inches='tight',pad_inches=.25)
plt.close(fig)

# Convert original app screenshot for monochrome academic report (source app unchanged)
img=Image.open(OUT+'/app_screenshot.png').convert('RGB')
gray=ImageOps.grayscale(img)
gray=ImageEnhance.Contrast(gray).enhance(1.1)
gray.save(OUT+'/app_screenshot_bw.png')
print('done',[(f,os.path.getsize(OUT+'/'+f)) for f in ['uml_class_bw.png','uml_overview_bw.png','uml_sequence_bw.png','app_screenshot_bw.png']])
