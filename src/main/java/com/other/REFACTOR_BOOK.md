# 重构

- 给程序添加一个特性，但发现程序缺乏良好的结构而不易于进行更改，那就先重构程序，使其容易添加改特性然后再添加特性。
- 重构前先检查自己是否有一套可靠的测试集，这些测试必须有自我检验的能力。
- 重构技术就是以微笑的步伐修改程序，如果犯错，很容易便可以发现它。
- 好的代码的检验标准是人们是否可以轻易的修改

## 重构的原则

为何重构

1. 重构改进软件的设计：
    - 经常性的重构有助于代码维持该有的形态，保护其原有设计。
    - 消除重复代码，为优秀设计根本。以防只修改一处，消除隐藏bug隐患。
2. 重构使软件更容易理解
3. 重构帮助找到bug
4. 重构提高编程速度

何时重构：

1. 预备性重构：让添加新功能更容易
2. 帮助理解的重构：使代码更易懂
3. 捡垃圾式重构：遇到即简单重构或者记录后续进行重构
4. 有计划的重构和见机的重构
5. 长期重构：先引入一层抽象，使其兼容新旧接口。
6. 复审代码时重构

> 重写比重构更容易时就不需要重构了

重构的挑战：

1. 延缓新功能的开发
2. 代码所有权：不同team拥有的代码权限不一样，可以将对外API改成`@deprected`，再进一步淘汰。
3. 分支：重构分支开发越久，`merge master`冲突越多，造成大量冲突。进行重构的功能拆分，分步进行。
4. 测试
5. 遗留代码：历史代码复杂又测试不足。逐步推进完善测试后进行重构。
6. 数据库：重构改变数据结构。使用渐进式数据库设计，数据库重构分散到多次生产发布来完成，引入新旧字段兼容，再逐步推进。

## 重构案例

### 简单的原则

- 提炼函数
- 引入参数对象
- 拆分阶段：一段代码处理不同的事情
- 内联类：一个类不再承担足够的责任，不再有单独存在的理由则存放到一个最频繁使用的类中。
- 替换函数：管道替换循环，简化函数逻辑等
- 以管道取代循环
- 以查询取代派生变量：有一些变量为类中对象运算而来。提供查询方法，以防变量改变未修改的情况
- 引入断言
- 查询和修改函数分离
- 以工厂函数取代构造函数：构造函数名称固定，且必须返回对象指针，并且需要`new`的特殊关键字声明。而普通函数可以进行更多的处理，并用方法名表示出意图。
- 以子类取代类型码：结合多态取代条件表达式

简化条件表达式：

1. 分解条件表达式：提炼函数即使用函数单一职责的方式抽取对象，三元表达式取代`if ... else...`
2. 合并条件表达式

### 隐藏委托关系

一个好的模块化的设计，“封装”即使不是其最关键特征，也是最关键特征之一。\
如果某些客户端先通过服务对象的字段得到另一个对象（受托类），然后调用后者的函数，那么客户就必须知晓这一层**委托**关系。万一受托类修改了接口，变化会波及通过服务对象使用它的所有客户端。我可以在服务对象上放置一个简单的**委托**
函数，将委托关系隐藏起来，从而去除这种依赖。这么一来，即使将来委托关系发生变化，变化也只会影响服务对象，而不会直接波及所有客户端。

##### 做法

- 对于每个委托关系中的函数，在服务对象端建立一个简单的委托函数。
- 调整客户端，令它只调用服务对象提供的函数。每次调整后运行测试。
- 如果将来不再有任何客户端需要取用 Delegate（受托类），便可移除服务对象中的相关访问函数。
- 测试。

#### 范例

class Person
```javascript
constructor(name) {
 this._name = name;
}
get name() {return this._name;}
get department()    {return this._department;}
set department(arg) {this._department = arg;}
```

class Department...
```javascript
get chargeCode() {return this._chargeCode;}
set chargeCode(arg) {this._chargeCode = arg;}
get manager() {return this._manager;}
set manager(arg) {this._manager = arg;}
```

修改后：

class Person...

```javascript
get manager() {return this._department.manager;}
```

### 移除中间人

每当客户端要使用受托类的新特性时，你就必须在服务端添加一个简单委托函数。随着受托类的特性（功能）越来越多，更多的转发函数就会使人烦躁。服务类完全变成了一个中间人（81），此时就应该让客户直接调用受托类。

class Person...移除

```javascript
get manager() {return this._department.manager;}
```

客户端代码...

```javascript
manager = aPerson.manager;
```

修改后

客户端代码...

```javascript
manager = aPerson.department.manager;
```

### 以卫语句取代潜逃条件表达式

条件表达式通常有两种风格。第一种风格是：两个条件分支都属于正常行为。第二种风格则是：只有一个条件分支是正常行为，另一个分支则是异常的情况。

"卫语句"(guard clauses) ：条件不满足立即返回，单独检查的判断语句。

#### 范例

```javascript
function payAmount(employee) {
    let result;
    if (employee.isSeparated) {
        result = {amount: 0, reasonCode: "SEP"};
    } else {
        if (employee.isRetired) {
            result = {amount: 0, reasonCode: "RET"};
        } else {
            // logic to compute amount
            lorem.ipsum(dolor.sitAmet);
            1
            consectetur(adipiscing).elit();
            sed.do.eiusmod = tempor.incididunt.ut(labore) &amp;&amp; dolore(magna.aliqua);
            ut.enim.ad(minim.veniam);
            result = someFinalComputation();
        }
    }
    return result;
}
```

修改：

```javascript
function payAmount(employee) {
    let result;
    if (employee.isSeparated) return {amount: 0, reasonCode: "SEP"};
    if (employee.isRetired) return {amount: 0, reasonCode: "RET"};
    // logic to compute amount
    lorem.ipsum(dolor.sitAmet);
    consectetur(adipiscing).elit();
    sed.do.eiusmod = tempor.incididunt.ut(labore) &amp;&amp; dolore(magna.aliqua);
    ut.enim.ad(minim.veniam);
    return someFinalComputation();
}
```

范例：将条件反转

```javascript
function adjustedCapital(anInstrument) {
    let result = 0;
    if (anInstrument.capital > 0) {
        if (anInstrument.interestRate > 0 &amp;&amp; anInstrument.duration > 0) {
            result = (anInstrument.income / anInstrument.duration) * anInstrument.adjustmentFactor;
        }
    }
    return result;
}
```

修改：

```javascript
function adjustedCapital(anInstrument) {
    if (anInstrument.capital &lt;= 0
        || anInstrument.interestRate &lt;= 0
        || anInstrument.duration &lt;= 0) return 0;
    return (anInstrument.income / anInstrument.duration) * anInstrument.adjustmentFactor;
}
```

### 多态取代条件表达式

```javascript
switch (bird.type) {
    case 'EuropeanSwallow':
        return "average";
    case 'AfricanSwallow':
        return (bird.numberOfCoconuts > 2) ? "tired" : "average";
    case 'NorwegianBlueParrot':
        return (bird.voltage > 100) ? "scorched" : "beautiful";
    default:
        return "unknown";
}

class EuropeanSwallow {
    get plumage() {
        return "average";
    }
}

class AfricanSwallow {
    get plumage() {
        return (this.numberOfCoconuts > 2) ? "tired" : "average";
    }
}

class NorwegianBlueParrot {
    get plumage() {
        return (this.voltage > 100) ? "scorched" : "beautiful";
    }
}
```

复杂的条件逻辑是编程中最难理解的东西之一。很多时候，我发现可以将条件逻辑拆分到不同的场景（或者叫高阶用例），从而拆解复杂的条件逻辑。这种拆分有时用条件逻辑本身的结构就足以表达，但使用类和多态能把逻辑的拆分表述得更清晰。

一个常见的场景是：我可以构造一组类型，每个类型处理各自的一种条件逻辑。例如，我会注意到，图书、音乐、食品的处理方式不同，这是因为它们分属不同类型的商品。最明显的征兆就是有好几个函数都有基于类型代码的 switch
语句。若果真如此，我就可以针对 switch 语句中的每种分支逻辑创建一个类，用多态来承载各个类型特有的行为，从而去除重复的分支逻辑。

另一种情况是：有一个基础逻辑，在其上又有一些变体。基础逻辑可能是最常用的，也可能是最简单的。我可以把基础逻辑放进超类，这样我可以首先理解这部分逻辑，暂时不管各种变体，然后我可以把每种变体逻辑单独放进一个子类，其中的代码着重强调与基础逻辑的差异。

多态是面向对象编程的关键特性之一。跟其他一切有用的特性一样，它也很容易被滥用。我曾经遇到有人争论说所有条件逻辑都应该用多态取代。我不赞同这种观点。我的大部分条件逻辑只用到了基本的条件语句——if/else 和
switch/case，并不需要劳师动众地引入多态。但如果发现如前所述的复杂条件逻辑，多态是改善这种情况的有力工具。

#### 做法

- 如果现有的类尚不具备多态行为，就用工厂函数创建之，令工厂函数返回恰当的对象实例。
- 在调用方代码中使用工厂函数获得对象实例。
- 将带有条件逻辑的函数移到超类中。
- 如果条件逻辑还未提炼至独立的函数，首先对其使用提炼函数（106）。
- 任选一个子类，在其中建立一个函数，使之覆写超类中容纳条件表达式的那个函数。将与该子类相关的条件表达式分支复制到新函数中，并对它进行适当调整。
- 重复上述过程，处理其他条件分支。
- 在超类函数中保留默认情况的逻辑。或者，如果超类应该是抽象的，就把该函数声明为 abstract，或在其中直接抛出异常，表明计算责任都在子类中。

#### 范例

```javascript
function plumages(birds) {
    return new Map(birds.map(b => [b.name, plumage(b)])
    )
        ;
}

function speeds(birds) {
    return new Map(birds.map(b => [b.name, airSpeedVelocity(b)])
    )
        ;
}

function plumage(bird) {
    switch (bird.type) {
        case 'EuropeanSwallow':
            return "average";
        case 'AfricanSwallow':
            return (bird.numberOfCoconuts > 2) ? "tired" : "average";
        case 'NorwegianBlueParrot':
            return (bird.voltage > 100) ? "scorched" : "beautiful";
        default:
            return "unknown";
    }
}

function airSpeedVelocity(bird) {
    switch (bird.type) {
        case 'EuropeanSwallow':
            return 35;
        case 'AfricanSwallow':
            return 40 - 2 * bird.numberOfCoconuts;
        case 'NorwegianBlueParrot':
            return (bird.isNailed) ? 0 : 10 + bird.voltage / 10;
        default:
            return null;
    }
}
```

修改后代码大致如下（我还对顶层的 airSpeedVelocity 和 plumage 函数做了内联处理）：

```javascript
function plumages(birds) {
    return new Map(birds
        .map(b => createBird(b))
        .map(bird => [bird.name, bird.plumage]
        ))
        ;
}

function speeds(birds) {
    return new Map(birds
        .map(b => createBird(b))
        .map(bird => [bird.name, bird.airSpeedVelocity]
        ))
        ;
}

function createBird(bird) {
    switch (bird.type) {
        case 'EuropeanSwallow':
            return new EuropeanSwallow(bird);
        case 'AfricanSwallow':
            return new AfricanSwallow(bird);
        case 'NorwegianBlueParrot':
            return new NorwegianBlueParrot(bird);
        default:
            return new Bird(bird);
    }
}

class Bird {
    constructor(birdObject) {
        Object.assign(this, birdObject);
    }

    get plumage() {
        return "unknown";
    }

    get airSpeedVelocity() {
        return null;
    }
}

class EuropeanSwallow extends Bird {
    get plumage() {
        return "average";
    }

    get airSpeedVelocity() {
        return 35;
    }
}

class AfricanSwallow extends Bird {
    get plumage() {
        return (this.numberOfCoconuts > 2) ? "tired" : "average";
    }

    get airSpeedVelocity() {
        return 40 - 2 * this.numberOfCoconuts;
    }
}

class NorwegianBlueParrot extends Bird {
    get plumage() {
        return (this.voltage > 100) ? "scorched" : "beautiful";
    }

    get airSpeedVelocity() {
        return (this.isNailed) ? 0 : 10 + this.voltage / 10;
    }
}
```

**范例：用多态处理变体逻辑**
有一家评级机构，要对远洋航船的航行进行投资评级。这家评级机构会给出“A”或者“B”两种评级，取决于多种风险和盈利潜力的因素。在评估风险时，既要考虑航程本身的特征，也要考虑船长过往航行的历史。

代码中有两处同样的条件逻辑，都在询问“是否有到中国的航程”以及“船长是否曾去过中国”。

```javascript
function rating(voyage, history) {
    const vpf = voyageProfitFactor(voyage, history);
    const vr = voyageRisk(voyage);
    const chr = captainHistoryRisk(voyage, history);
    if (vpf * 3 > (vr + chr * 2)) return "A";
    else return "B";
}

function voyageRisk(voyage) {
    let result = 1;
    if (voyage.length > 4) result += 2;
    if (voyage.length > 8) result += voyage.length - 8;
    if (["china", "east-indies"].includes(voyage.zone)) result += 4;
    return Math.max(result, 0);
}

function captainHistoryRisk(voyage, history) {
    let result = 1;
    if (history.length &lt; 5) result += 4;
    result += history.filter(v => v.profit &lt; 0
    )
    length;
    if (voyage.zone === "china" &amp;&amp; hasChina(history)) result -= 2;
    return Math.max(result, 0);
}

function hasChina(history) {
    return history.some(v => "china" === v.zone
    )
        ;
}

function voyageProfitFactor(voyage, history) {
    let result = 2;
    if (voyage.zone === "china") result += 1;
    if (voyage.zone === "east-indies") result += 1;
    if (voyage.zone === "china" &amp;&amp; hasChina(history)) {
        result += 3;
        if (history.length > 10) result += 1;
        if (voyage.length > 12) result += 1;
        if (voyage.length > 18) result -= 1;
    } else {
        if (history.length > 8) result += 1;
        if (voyage.length > 14) result -= 1;
    }
    return result;
}
```

调用方的代码大概是这样：

```javascript
const voyage = {zone: "west-indies", length: 10};
const history = [
    {zone: "east-indies", profit: 5},
    {zone: "west-indies", profit: 15},
    {zone: "china", profit: -2},
    {zone: "west-africa", profit: 7},
];

const myRating = rating(voyage, history);
```

修改后：有一个基本的 Rating 类，其中不考虑与“中国经验”相关的复杂性。与“中国经验”相关的代码则清晰表述出在基本逻辑之上的一系列变体逻辑：

```javascript
class Rating {
    constructor(voyage, history) {
        this.voyage = voyage;
        this.history = history;
    }

    get value() {
        const vpf = this.voyageProfitFactor;
        const vr = this.voyageRisk;
        const chr = this.captainHistoryRisk;
        if (vpf * 3 > (vr + chr * 2)) return "A";
        else return "B";
    }

    get voyageRisk() {
        let result = 1;
        if (this.voyage.length > 4) result += 2;
        if (this.voyage.length > 8) result += this.voyage.length - 8;
        if (["china", "east-indies"].includes(this.voyage.zone)) result += 4;
        return Math.max(result, 0);
    }

    get captainHistoryRisk() {
        let result = 1;
        if (this.history.length &lt; 5) result += 4;
        result += this.history.filter(v => v.profit &lt; 0
        ).length;
        return Math.max(result, 0);
    }

    get voyageProfitFactor() {
        let result = 2;
        if (this.voyage.zone === "china") result += 1;
        if (this.voyage.zone === "east-indies") result += 1;
        result += this.historyLengthFactor;
        result += this.voyageLengthFactor;
        return result;
    }

    get voyageLengthFactor() {
        return (this.voyage.length > 14) ? -1 : 0;
    }

    get historyLengthFactor() {
        return (this.history.length > 8) ? 1 : 0;
    }
}
```

```javascript
class ExperiencedChinaRating extends Rating {
    get captainHistoryRisk() {
        const result = super.captainHistoryRisk - 2;
        return Math.max(result, 0);
    }

    get voyageLengthFactor() {
        let result = 0;
        if (this.voyage.length > 12) result += 1;
        if (this.voyage.length > 18) result -= 1;
        return result;
    }

    get historyLengthFactor() {
        return (this.history.length > 10) ? 1 : 0;
    }

    get voyageProfitFactor() {
        return super.voyageProfitFactor + 3;
    }
}
```

### 移除标记参数

以明确函数取代参数

“标记参数”是这样的一种参数：调用者用它来指示被调函数应该执行哪一部分逻辑

```javascript
function bookConcert(aCustomer, isPremium) {
    if (isPremium) {
        // logic for premium booking
    } else {
        // logic for regular booking
    }
}
```

##### 范例

调用方

```javascript
aShipment.deliveryDate = deliveryDate(anOrder, false);
```

方法体

```javascript
function deliveryDate(anOrder, isRush) {
    if (isRush) {
        let deliveryTime;
        if (["MA", "CT"].includes(anOrder.deliveryState)) deliveryTime = 1;
        else if (["NY", "NH"].includes(anOrder.deliveryState)) deliveryTime = 2;
        else deliveryTime = 3;
        return anOrder.placedOn.plusDays(1 + deliveryTime);
    } else {
        let deliveryTime;
        if (["MA", "CT", "NY"].includes(anOrder.deliveryState)) deliveryTime = 2;
        else if (["ME", "NH"].includes(anOrder.deliveryState)) deliveryTime = 3;
        else deliveryTime = 4;
        return anOrder.placedOn.plusDays(2 + deliveryTime);
    }
}
```

修改后：

```javascript
function rushDeliveryDate(anOrder) {
    let deliveryTime;
    if (["MA", "CT"].includes(anOrder.deliveryState)) deliveryTime = 1;
    else if (["NY", "NH"].includes(anOrder.deliveryState)) deliveryTime = 2;
    else deliveryTime = 3;
    return anOrder.placedOn.plusDays(1 + deliveryTime);
}

function regularDeliveryDate(anOrder) {
    let deliveryTime;
    if (["MA", "CT", "NY"].includes(anOrder.deliveryState)) deliveryTime = 2;
    else if (["ME", "NH"].includes(anOrder.deliveryState)) deliveryTime = 3;
    else deliveryTime = 4;
    return anOrder.placedOn.plusDays(2 + deliveryTime);
}
```

调用方,可以直接明确调用的方法

```javascript
aShipment.deliveryDate = rushDeliveryDate(anOrder);
```

### 以命令取代函数

将函数封装成自己的对象，这样的对象我称之为“命令对象”（command object），或者简称“命令”（command）。这种对象大多只服务于单一函数，获得对该函数的请求，执行该函数，就是这种对象存在的意义。

与普通的函数相比，命令对象提供了更大的控制灵活性和更强的表达能力。除了函数调用本身，命令对象还可以支持附加的操作，例如撤销操作。我可以通过命令对象提供的方法来设值命令的参数值，从而支持更丰富的生命周期管理能力。我可以借助继承和钩子对函数行为加以定制。如果我所使用的编程语言支持对象但不支持函数作为一等公民，通过命令对象就可以给函数提供大部分相当于一等公民的能力。

命令对象为处理复杂计算提供了强大的机制。借助命令对象，可以轻松地将原本复杂的函数拆解为多个方法，彼此之间通过字段共享状态；拆解后的方法可以分别调用；开始调用之前的数据状态也可以逐步构建。

##### 做法

- 为想要包装的函数创建一个空的类，根据该函数的名字为其命名。
- 使用搬移函数（198）把函数移到空的类里。
- 保持原来的函数作为转发函数，至少保留到重构结束之前才删除。
- 遵循编程语言的命名规范来给命令对象起名。如果没有合适的命名规范，就给命令对象中负责实际执行命令的函数起一个通用的名字，例如“execute”或者“call”。
- 可以考虑给每个参数创建一个字段，并在构造函数中添加对应的参数。

#### 范例

```javascript
function score(candidate, medicalExam, scoringGuide) {
    let result = 0;
    let healthLevel = 0;
    let highMedicalRiskFlag = false;

    if (medicalExam.isSmoker) {
        healthLevel += 10;
        highMedicalRiskFlag = true;
    }
    let certificationGrade = "regular";
    if (scoringGuide.stateWithLowCertification(candidate.originState)) {
        certificationGrade = "low";
        result -= 5;
    } // lots more code like this
    result -= Math.max(healthLevel - 5, 0);
    return result;
}
```

进行命令对象抽象后，进而对方法进行重构：

```javascript
constructor(candidate, medicalExam, scoringGuide)
{
    this._candidate = candidate;
    this._medicalExam = medicalExam;
    this._scoringGuide = scoringGuide;
}

execute()
{
    this._result = 0;
    this._healthLevel = 0;
    this._highMedicalRiskFlag = false;

    if (this._medicalExam.isSmoker) {
        this._healthLevel += 10;
        this._highMedicalRiskFlag = true;
    }
    this._certificationGrade = "regular";
    if (this._scoringGuide.stateWithLowCertification(this._candidate.originState)) {
        this._certificationGrade = "low";
        this._result -= 5;
    }
    // lots more code like this
    this._result -= Math.max(this._healthLevel - 5, 0);
    return this._result;
}
```

### 以委托取代子类

如果一个对象的行为有明显的类别之分，继承是很自然的表达方式。我可以把共用的数据和行为放在超类中，每个子类根据需要覆写部分特性。在面向对象语言中，继承很容易实现，因此也是程序员熟悉的机制。

但**继承也有其短板**
。最明显的是，继承这张牌只能打一次。导致行为不同的原因可能有多种，但继承只能用于处理一个方向上的变化。比如说，我可能希望“人”的行为根据“年龄段”不同，并且根据“收入水平”不同。使用继承的话，子类可以是“年轻人”和“老人”，也可以是“富人”和“穷人”，但不能同时采用两种继承方式。

更大的问题在于，继承给类之间引入了非常紧密的关系。在超类上做任何修改，都很可能破坏子类，所以必须非常小心，并且充分理解子类如何从超类派生。

这两个问题用委托都能解决。对于不同的变化原因，我可以委托给不同的类。委托是对象之间常规的关系。与继承关系相比，使用委托关系时接口更清晰、耦合更少。因此，继承关系遇到问题时运用以委托取代子类是常见的情况。

“对象组合优于类继承”（“组合”跟“委托”是同一回事）。继承是一种很有价值的机制，大部分时候能达到效果，不会带来问题。所以我会从继承开始，如果开始出现问题，再转而使用委托。

#### **做法**

1. 如果构造函数有多个调用者，首先用以工厂函数取代构造函数（334）把构造函数包装起来。
2. 创建一个空的委托类，这个类的构造函数应该接受所有子类特有的数据项，并且经常以参数的形式接受一个指回超类的引用。
3. 在超类中添加一个字段，用于安放委托对象。
4. 修改子类的创建逻辑，使其初始化上述委托字段，放入一个委托对象的实例。
    - 这一步可以在工厂函数中完成，也可以在构造函数中完成（如果构造函数有足够的信息以创建正确的委托对象的话）。
5. 选择一个子类中的函数，将其移入委托类。
6. 使用搬移函数（198）手法搬移上述函数，不要删除源类中的委托代码。
    - 如果这个方法用到的其他元素也应该被移入委托对象，就把它们一并搬移。如果它用到的元素应该留在超类中，就在委托对象中添加一个字段，令其指向超类的实例。
7. 如果被搬移的源函数还在子类之外被调用了，就把留在源类中的委托代码从子类移到超类，并在委托代码之前加上卫语句，检查委托对象存在。如果子类之外已经没有其他调用者，就用移除死代码（237）去掉已经没人使用的委托代码。
    - 如果有多个委托类，并且其中的代码出现了重复，就使用提炼超类（375）手法消除重复。此时如果默认行为已经被移入了委托类的超类，源超类的委托函数就不再需要卫语句了。
8. 测试。
9. 重复上述过程，直到子类中所有函数都搬到委托类。
10. 找到所有调用子类构造函数的地方，逐一将其改为使用超类的构造函数。
11. 测试。
12. 运用移除死代码（237）去掉子类。

#### 范例：取代继承体系

这个关于鸟儿（bird）的系统很快要有一个大变化：有些鸟是“野生的”（wild），有些鸟是“家养的”（captive），两者之间的行为会有很大差异。这种差异可以建模为 Bird 类的两个子类：WildBird 和
CaptiveBird。但继承只能用一次，所以如果想用子类来表现“野生”和“家养”的差异，就得先去掉关于“不同品种”的继承关系。

```javascript
function createBird(data) {
    switch (data.type) {
        case 'EuropeanSwallow':
            return new EuropeanSwallow(data);
        case 'AfricanSwallow':
            return new AfricanSwallow(data);
        case 'NorweigianBlueParrot':
            return new NorwegianBlueParrot(data);
        default:
            return new Bird(data);
    }
}

class Bird {
    constructor(data) {
        this._name = data.name;
        this._plumage = data.plumage;
    }

    get name() {
        return this._name;
    }

    get plumage() {
        return this._plumage || "average";
    }

    get airSpeedVelocity() {
        return null;
    }
}

class EuropeanSwallow extends Bird {
    get airSpeedVelocity() {
        return 35;
    }
}

class AfricanSwallow extends Bird {
    constructor(data) {
        super(data);
        this._numberOfCoconuts = data.numberOfCoconuts;
    }

    get airSpeedVelocity() {
        return 40 - 2 * this._numberOfCoconuts;
    }
}

class NorwegianBlueParrot extends Bird {
    constructor(data) {
        super(data);
        this._voltage = data.voltage;
        this._isNailed = data.isNailed;
    }

    get plumage() {
        if (this._voltage > 100) return "scorched";
        else return this._plumage || "beautiful";
    }

    get airSpeedVelocity() {
        return (this._isNailed) ? 0 : 10 + this._voltage / 10;
    }
}
```

修改后

用一系列委托类取代了原来的多个子类，与原来非常相似的继承结构被转移到了 SpeciesDelegate 下面。除了给 Bird
类重新被继承的机会，从这个重构中我还有什么收获？新的继承体系范围更收拢了，只涉及各个品种不同的数据和行为，各个品种相同的代码则全都留在了 Bird 中，它未来的子类也将得益于这些共用的行为

```javascript
function createBird(data) {
    return new Bird(data);
}

class Bird {
    constructor(data) {
        this._name = data.name;
        this._plumage = data.plumage;
        this._speciesDelegate = this.selectSpeciesDelegate(data);
    }

    get name() {
        return this._name;
    }

    get plumage() {
        return this._speciesDelegate.plumage;
    }

    get airSpeedVelocity() {
        return this._speciesDelegate.airSpeedVelocity;
    }

    selectSpeciesDelegate(data) {
        switch (data.type) {
            case 'EuropeanSwallow':
                return new EuropeanSwallowDelegate(data, this);
            case 'AfricanSwallow':
                return new AfricanSwallowDelegate(data, this);
            case 'NorweigianBlueParrot':
                return new NorwegianBlueParrotDelegate(data, this);
            default:
                return new SpeciesDelegate(data, this);
        }
    }

    // rest of bird’s code...
}

// 注意这个委托超类抽象 
class SpeciesDelegate {
    constructor(data, bird) {
        this._bird = bird;
    }

    get plumage() {
        return this._bird._plumage || "average";
    }

    get airSpeedVelocity() {
        return null;
    }
}

class EuropeanSwallowDelegate extends SpeciesDelegate {
    get airSpeedVelocity() {
        return 35;
    }
}

class AfricanSwallowDelegate extends SpeciesDelegate {
    constructor(data, bird) {
        super(data, bird);
        this._numberOfCoconuts = data.numberOfCoconuts;
    }

    get airSpeedVelocity() {
        return 40 - 2 * this._numberOfCoconuts;
    }
}

class NorwegianBlueParrotDelegate extends SpeciesDelegate {
    constructor(data, bird) {
        super(data, bird);
        this._voltage = data.voltage;
        this._isNailed = data.isNailed;
    }

    get airSpeedVelocity() {
        return (this._isNailed) ? 0 : 10 + this._voltage / 10;
    }

    get plumage() {
        if (this._voltage > 100) return "scorched";
        else return this._bird._plumage || "beautiful";
    }
}
```

### 委托取代超类(取代继承)

有一个经典的误用继承的例子：让栈（stack）继承列表（list）。这个想法的出发点是想复用列表类的数据存储和操作能力。虽说复用是一件好事，但这个继承关系有问题：列表类的所有操作都会出现在栈类的接口上，然而其中大部分操作对一个栈来说并不适用。更好的做法应该是把列表作为栈的字段，把必要的操作委派给列表就行了。**
这就是一个用得上以委托取代超类手法的例子**

假如我有一个车模（car model）类，其中有名称、引擎大小等属性，我可能想复用这些特性来表示真正的汽车（car），并在子类上添加 VIN 编号、制造日期等属性。然而汽车终归不是模型。这是一种常见而又经常不易察觉的建模错误。

有问题的继承招致了混乱和错误——如果把继承关系改为将部分职能委托给另一个对象，这些混乱和错误本是可以轻松避免的。使用委托关系能更清晰地表达“这是另一个东西，我只是需要用到其中携带的一些功能”这层意思。

即便在子类继承是合理的建模方式的情况下，如果子类与超类之间的耦合过强，**超类的变化很容易破坏子类的功能**，我还是会使用以**委托取代超类**
。这样做的缺点就是，对于宿主类（也就是原来的子类）和委托类（也就是原来的超类）中原本一样的函数，现在我必须在宿主类中挨个编写转发函数。不过还好，这种转发函数虽然写起来乏味，但它们都非常简单，几乎不可能出错。

**首先（尽量）使用继承，如果发现继承有问题，再使用以委托取代超类。**

#### 做法

1. 在子类中新建一个字段，使其引用超类的一个对象，并将这个委托引用初始化为超类的新实例。
2. 针对超类的每个函数，在子类中创建一个转发函数，将调用请求转发给委托引用。每转发一块完整逻辑，都要执行测试。
3. 大多数时候，每转发一个函数就可以测试，但一对设值/取值必须同时转移，然后才能测试。
4. 当所有超类函数都被转发函数覆写后，就可以去掉继承关系。

#### 范例

我最近给一个古城里存放上古卷轴（scroll）的图书馆做了咨询。他们给卷轴的信息编制了一份目录（catalog），每份卷轴都有一个 ID 号，并记录了卷轴的标题（title）和一系列标签（tag）。

class CatalogItem...

```javascript
constructor(id, title, tags)
{
    this._id = id;
    this._title = title;
    this._tags = tags;
}

get
id()
{
    return this._id;
}
get
title()
{
    return this._title;
}
hasTag(arg)
{
    return this._tags.includes(arg);
}
```

这些古老的卷轴需要日常清扫，因此代表卷轴的 Scroll 类继承了代表目录项的 CatalogItem 类，并扩展出与“需要清扫”相关的数据。 这就是一个**常见的建模错误**
。真实存在的卷轴和只存在于纸面上的目录项，是完全不同的两种东西。比如说，关于“如何治疗灰鳞病”的卷轴可能有好几卷，但在目录上却只记录一个条目。 class Scroll extends CatalogItem...

```javascript
constructor(id, title, tags, dateLastCleaned)
{
    super(id, title, tags);
    this._lastCleaned = dateLastCleaned;
}

needsCleaning(targetDate)
{
    const threshold = this.hasTag("revered") ? 700 : 1500;
    return this.daysSinceLastCleaning(targetDate) > threshold;
}
daysSinceLastCleaning(targetDate)
{
    return this._lastCleaned.until(targetDate, ChronoUnit.DAYS);
}
```

修改后： 用传入的 catalogID 来查找对应的 CatalogItem 对象，并引用这个对象（而不再新建 CatalogItem 对象） class Scroll...

```javascript
constructor(id, dateLastCleaned, catalogID, catalog)
{
    this._id = id;
    this._catalogItem = catalog.get(catalogID);
    this._lastCleaned = dateLastCleaned;
}
```

加载程序...

```javascript
const scrolls = aDocument
    .map(record => new Scroll(record.id,
        LocalDate.parse(record.lastCleaned),
        record.catalogData.id,
        catalog));
```
