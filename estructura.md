├── src
│   ├── client
│   │   ├── java
│   │   │   └── com
│   │   │       └── hollow
│   │   │           └── client
│   │   │               ├── mixin
│   │   │               │   └── ExampleClientMixin.java
│   │   │               ├── RubyModClient.java
│   │   │               └── RubyModDataGenerator.java
│   │   └── resources
│   │       └── ruby-mod.client.mixins.json
│   └── main
│       ├── java
│       │   └── com
│       │       └── hollow
│       │           ├── mixin
│       │           │   └── ExampleMixin.java
│       │           ├── registry
│       │           │   ├── Blocks.java
│       │           │   ├── ItemGroups.java
│       │           │   ├── Items.java
│       │           │   └── ToolMaterials.java
│       │           ├── worldgen
│       │           │   └── ModOreGeneration.java
│       │           └── RubyMod.java
│       └── resources
│           ├── assets
│           │   └── ruby-mod
│           │       ├── blockstates
│           │       │   ├── deepslate_ruby_ore.json
│           │       │   ├── ruby_block.json
│           │       │   └── ruby_ore.json
│           │       ├── lang
│           │       │   └── es_es.json
│           │       ├── models
│           │       │   ├── block
│           │       │   │   ├── deepslate_ruby_ore.json
│           │       │   │   ├── ruby_block.json
│           │       │   │   └── ruby_ore.json
│           │       │   └── item
│           │       │       ├── deepslate_ruby_ore.json
│           │       │       ├── ruby.json
│           │       │       ├── ruby_block.json
│           │       │       └── ruby_ore.json
│           │       ├── textures
│           │       │   ├── block
│           │       │   │   ├── deepslate_ruby_ore.png
│           │       │   │   ├── ruby_block.png
│           │       │   │   └── ruby_ore.png
│           │       │   └── item
│           │       │       ├── ruby.png
│           │       │       ├── ruby_axe.png
│           │       │       ├── ruby_hoe.png
│           │       │       ├── ruby_pickaxe.png
│           │       │       ├── ruby_shovel.png
│           │       │       └── ruby_sword.png
│           │       └── icon.png
│           ├── data
│           │   ├── minecraft
│           │   │   └── tags
│           │   │       └── block
│           │   │           ├── mineable
│           │   │           │   └── pickaxe.json
│           │   │           └── needs_iron_tool.json
│           │   └── ruby-mod
│           │       ├── loot_table
│           │       │   └── blocks
│           │       │       ├── deepslate_ruby_ore.json
│           │       │       ├── ruby_block.json
│           │       │       └── ruby_ore.json
│           │       ├── recipe
│           │       │   ├── ruby_block.json
│           │       │   └── ruby_from_block.json
│           │       └── worldgen
│           │           ├── configured_feature
│           │           │   ├── deepslate_ruby_ore.json
│           │           │   └── ruby_ore.json
│           │           └── placed_feature
│           │               ├── deepslate_ruby_ore.json
│           │               └── ruby_ore.json
│           ├── fabric.mod.json
│           └── ruby-mod.mixins.json
├── build.gradle
├── gradle.properties
└── settings.gradle
