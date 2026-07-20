import { useEffect, useState } from 'react'
import { Center, Flex, Grid, Text } from '@chakra-ui/react'

import "primeicons/primeicons.css";

import SuGrid from './components/SuGrid';
import Square from './components/Square';
import Section from './components/Section';

function App() {
  const debug = true;

  const isPortrait = window.innerHeight > window.innerWidth;

  const [useSC, setSC] = useState([]);

  const [useMode, setMode] = useState(true);

  const emptyGrid = Array.from({ length: 9 }, (_, X) =>
    Array.from({ length: 9 }, (_, Y) => ({
      value: '',
      position: [X, Y],
      selected: false,
    }))
  );

  const [useGrid, setGrid] = useState(emptyGrid);

  const [useRot, setRot] = useState(isPortrait);

  const clearGrid = () => {
    setSC(null);
    setGrid(emptyGrid);
  }

  const setValue = (position, label, value) => {
    if (position.length != 2) { if (debug) console.log('Error: Failed set the value, position values missing or incorrect!'); return false; }

    setGrid(prev => {
      const copy = [...prev];

      copy[position[0]] = [...copy[position[0]]];
      copy[position[0]][position[1]] = {
        ...copy[position[0]][position[1]],
        [label]: value
      };

      if (debug) console.log(copy)

      return copy;
    });
  };

  const clearSelection = () => {
    setValue(useSC, 'selected', false);
    setSC([]);
  }

  const lineInsert = (array, ind, newLine) => {
    if (!Array.isArray(array) || !Array.isArray(newLine) || newLine.length != 9) { if (debug) console.log('Error: Provided list isnt a array or its length is incorrect!'); return null; }

    array[ind] = newLine.map((cell) => ({ ...cell }));
  }

  const buttons = Array.from({ length: 9 }, (_, ind) => ({
    value: (1 + ind),
    onClick: () => setValue(useSC, 'value', (1 + ind)),
  }));

  const valueCheck = (value, mode) => {
    if (value == '' && typeof (mode) == 'number' && mode == 1) return false;

    for (let x = 1; x <= 9; x++) {
      if (value == x || (value == '' && typeof (mode) == 'number' && mode == 0)) return true;
    }

    return false;
  }

  const validateGrid = (mode, array) => {
    if (typeof (mode) != 'number' || !Array.isArray(array)) { if (debug) console.log('Error: Failed to validate the grid, provided ivalid mode or array!'); return false; }
    // mode 0 = grid creation; mode 1 = grid check;
    for (let col = 0; col < 9; col++) {
      const blackList = [];
      for (let row = 0; row < 9; row++) {
        const val = array[row][col]['value'];

        if (!valueCheck(val, mode)) { if (debug) console.log('Error: Cell have invalid value!'); return false; }

        if (val != '') {
          if (!blackList.includes(val)) blackList.push(val);
          else { if (debug) console.log('Error: Grid validation failed, values repeated!'); return false; }
        }
      }
    }
    return true;
  }

  const positionRec = (array) => {
    const copy = array.map((cell, Y) => ({ ...cell, position: [cell.position[0], Y] }));

    return copy;
  }

  const shuffle = (array) => array.sort(() => Math.random() - 0.5);

  const lineCreate = (lineInd) => {
    if (typeof (lineInd) != 'number') { if (debug) console.log('Error: Line id is not valid!'); return null; }

    const line = Array.from({ length: 9 }, (_, Y) => ({
      value: (1 + Y),
      position: [lineInd, Y],
      selected: false,
    }));

    shuffle(line);

    return positionRec(line);
  }

  const lineRecovery = (line) => {
    
  }

  const generateGrid = () => {
    const newArray = emptyGrid.map((row) => row.map((cell) => ({ ...cell })));

    for (let i = 0; i < 9; i++) {
      const newLine = lineCreate(i);

      lineRecovery(newLine);

      lineInsert(newArray, i, newLine);
    }

    return newArray;
  }

  const gridCreation = () => {
    const newGrid = generateGrid();

    //const newGrid = removeValues(newGrid);

    setGrid(newGrid);
  }

  const tools = [
    { value: (<i className='pi pi-pencil' style={{ fontWeight: 'bold' }}></i>), type: 'edi', onClick: () => setMode(true) },
    { value: (<i className='pi pi-thumbtack' style={{ fontWeight: 'bold' }}></i>), type: 'mar', onClick: () => setMode(false) },
    { value: (<i className='pi pi-eraser' style={{ fontWeight: 'bold' }}></i>), type: 'cle', onClick: () => setValue(useSC, 'value', '') },
    { value: (<i className='pi pi-check-circle' style={{ fontWeight: 'bold' }}></i>), type: 'che', onClick: () => { console.log('has been clicked'); validateGrid(1, useGrid); } },
    { value: (<i className='pi pi-trash' style={{ fontWeight: 'bold' }}></i>), type: 'era', onClick: clearGrid },
    { value: (<i className='pi pi-refresh' style={{ fontWeight: 'bold' }}></i>), type: 'res', onClick: gridCreation },
  ];

  useEffect(() => {
    setRot(!useRot);
  }, [isPortrait]);

  useEffect(() => {
    gridCreation();
  }, []);

  return (
    <Center w={'100%'} h={'100vh'} flexDir={useRot ? 'row' : 'column'} gap={{ base: 5, smToMd: '1rem', md: '2rem' }}>

      <Text fontWeight={'medium'} fontSize={'5xl'}>Sudoku</Text>
      <SuGrid >
        {
          useGrid?.map((rows, ind) => {
            return rows?.map((cell, ind) => {
              return (<Square fontSize={'lg'} fontWeight={'bolder'} backgroundColor={cell['selected'] ? 'white' : 'red'} key={'posInd' + ind} onClick={() => {
                if (debug) console.log('Cell (clicked) possition: (X,Y) ' + useSC?.[0] + ',' + useSC?.[1]);

                if (!cell['selected'] && useSC.length < 2) {
                  setSC([]);
                  setSC([...cell['position']]);
                  setValue(cell['position'], 'selected', true);
                }
                else if (!cell['selected'] && useSC.length == 2 && (useSC[0] !== cell['position'][0] || useSC[1] !== cell['position'][1])) {
                  clearSelection();
                  setSC([...cell['position']]);
                  setValue(cell['position'], 'selected', true);
                }
                else if (!!cell['selected'] && useSC.length == 2 && useSC[0] == cell['position'][0] && useSC[1] == cell['position'][1]) {
                  setValue(cell['position'], 'selected', false);
                  setSC([]);
                }

                if (debug) {
                  console.log('Cell (clicked) selection state: ' + cell['selected']);
                  if (useSC.length == 2) {
                    console.log('Cell selected right now: (X,Y) ' + useSC[0] + ',' + useSC[1]);
                    console.log('Cell (clicked) possition: (X,Y) ' + useSC[0] + ',' + useSC[1]);
                  }
                }
              }}>{cell['value']}</Square>)
            })
          })
        }
      </SuGrid>
      <Flex flexDir={useRot ? 'row' : 'column'} bg={'blue'} borderRadius={'xl'} p={{ base: 2, smToMd: 2, md: 3 }}>

        <Section rotation={useRot}>{
          buttons?.map((item, ind) => {
            return (<Square fontWeight={'bold'} key={'buttInd' + ind} onClick={() => item['onClick']()}>{item['value']}</Square>)
          })
        }
        </Section>
        <Section rotation={useRot} mt={useRot ? '' : { base: 2, smToMd: 2, md: 3 }} ms={useRot ? { base: 2, smToMd: 2, md: 3 } : ''}>{
          tools?.map((item, ind) => {
            return (<Square fontWeight={'bold'} backgroundColor={item['type'] == 'edi' && !!useMode || item['type'] == 'mar' && !useMode ? 'white' : 'red'} key={'toolButtInd' + ind} onClick={() => item['onClick']()}>{item['value']}</Square>)
          })
        }
        </Section>

      </Flex>

    </Center >
  )
}

export default App
